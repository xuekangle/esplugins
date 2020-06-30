package com.emptyxkl.featurescoretype;

import com.emptyxkl.utils.FeatureScoreUtils;
import com.emptyxkl.utils.TypeChangeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.LeafReaderContext;
import org.elasticsearch.common.xcontent.support.XContentMapValues;
import org.elasticsearch.index.fielddata.ScriptDocValues;
import org.elasticsearch.script.ScriptContext;
import org.elasticsearch.script.ScriptEngine;
import org.elasticsearch.script.SearchScript;
import org.elasticsearch.search.lookup.SearchLookup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author xxx
 * @date 2020/06/29
 */
public class MySciptEngine implements ScriptEngine {
    private final Logger logger = LogManager.getLogger(MySciptEngine.class);

    /**
     * script_name
     * @return
     */
    @Override
    public String getType() {
        return "featurescore";
    }

    @Override
    public <FactoryType> FactoryType compile(String scriptName, String scriptSource, ScriptContext<FactoryType> context, Map<String, String> params) {
        logger.info("use params the scriptName {} ,scriptSource  {}, context {},params {}", scriptName, scriptSource, context.name, params.entrySet());
        // use type search
        if (!context.equals(SearchScript.CONTEXT)) {
            throw new IllegalArgumentException(getType() + " scripts cannot be used for context [" + context.name + "]");
        }

        //获取查询语句中的feature属性
        String feature = XContentMapValues.nodeStringValue(params.get("feature"),null);
        String[] features = feature.split(",");
        logger.info("features : {} ", Arrays.toString(features));
        if(null == features || features.length == 0){
            return null;
        }
        List<float[]> feascoreList = new ArrayList<>();
        for(String str:features){
            if(str == null || "".equals(str)){
                continue;
            }
            float[] feascore = TypeChangeUtils.Bs64String2float(str);
            feascoreList.add(feascore);
        }

        // query script use "source" value with identifier
        if ("featurescore".equals(scriptSource)) {
            SearchScript.Factory factory = (p, lookup) -> new SearchScript.LeafFactory() {
                @Override
                public SearchScript newInstance(LeafReaderContext context) throws IOException {
                    return new SearchScript(p, lookup, context) {
                        @Override
                        public double runAsDouble() {
                            try{
                                //从es中取出数据，跟查询传入的featureList做比较
                                byte[] sourceFeature = getDocValues(lookup,"feature");

                                Double minScore = Double.MAX_VALUE;
                                for (float[] feascore:feascoreList) {
                                    Double score = FeatureScoreUtils.getScore(feascore, sourceFeature);
                                    minScore = score < minScore ? score : minScore;
                                }
                                return minScore;
                            }catch (Exception e){
                                return Double.MAX_VALUE;
                            }
                        }
                    };
                }

                @Override
                public boolean needs_score() {
                    return false;
                }
            };

            return context.factoryClazz.cast(factory);
        }
        throw new IllegalArgumentException("Unknown script name " + scriptSource);
    }

    private byte[] getDocValues(SearchLookup lookup, String feature) {
        final ScriptDocValues.BytesRefs bytesRefs = (ScriptDocValues.BytesRefs)lookup.source().get(feature);
        //return getDocValues(bytesRefs.getValue().bytes);
        return bytesRefs.getValue().bytes;
    }
}
