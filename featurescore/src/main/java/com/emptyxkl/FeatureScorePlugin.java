package com.emptyxkl;

import com.emptyxkl.utils.FeatureScoreUtils;
import com.emptyxkl.utils.TypeChangeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.LeafReaderContext;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.support.XContentMapValues;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.ScriptPlugin;
import org.elasticsearch.script.ScoreScript;
import org.elasticsearch.script.ScriptContext;
import org.elasticsearch.script.ScriptEngine;
import org.elasticsearch.search.lookup.SearchLookup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class FeatureScorePlugin extends Plugin implements ScriptPlugin {

    private static final Logger logger = LogManager.getLogger(FeatureScorePlugin.class);

    @Override
    public ScriptEngine getScriptEngine(Settings settings, Collection<ScriptContext<?>> contexts) {
        return new MySciptEngine();
    }

    public static class MySciptEngine implements ScriptEngine {
        /**
         * script_name
         * 对应查询语句中的{"lang":"score_script"}
         *
         * @return
         */
        @Override
        public String getType() {
            return "score_script";
        }


        @Override
        public <T> T compile(String scriptName, String scriptSource, ScriptContext<T> context, Map<String, String> params) {
//            logger.info("use params the scriptName {} ,scriptSource  {}, context {},params {}", scriptName, scriptSource, context.name, params.entrySet());

            if (!context.equals(ScoreScript.CONTEXT)) {
                throw new IllegalArgumentException(getType() + " scripts cannot be used for context [" + context.name + "]");
            }

            //对应查询语句中的{"inline":"featurescore"},inline换成source也行，两者只能填一个
            if ("featurescore".equals(scriptSource)) {
                //p:查询语句中的params
                ScoreScript.Factory factory = (p, lookup) -> new ScoreScript.LeafFactory() {
                    @Override
                    public ScoreScript newInstance(LeafReaderContext context) throws IOException {
                        return new ScoreScript(p, lookup, context) {
                            @Override
                            public double execute() {
//                                logger.info("------------------------------------------");
//                                logger.info("p----------------->{}", p.entrySet());
                                //获取查询语句中的feature属性
                                String feature = XContentMapValues.nodeStringValue(p.get("feature"), null);
//                                logger.info("feature:{}", feature);
                                String[] features = feature.split(",");
//                                logger.info("features : {} ", Arrays.toString(features));
                                if (features.length == 0) {
                                    throw new IllegalArgumentException("features is null");
                                }
                                final List<float[]> feascoreList = new ArrayList<>();
                                for (String str : features) {
                                    if (str == null || "".equals(str)) {
                                        continue;
                                    }
                                    float[] feascore = TypeChangeUtils.Bs64String2float(str);
//                                    logger.info("feascore:{}", Arrays.toString(feascore));
                                    feascoreList.add(feascore);
                                }
                                try {
//                                    logger.info("get data from es!!!!!!!!!!!!!!!!");
                                    //从es中取出各个document的feature的数据，跟查询传入的featureList做比较
                                    byte[] sourceFeature = getDocValues(lookup,context, "feature");
//                                    logger.info("esFeature byte:{}", Arrays.toString(sourceFeature));

                                    double minScore = Double.MAX_VALUE;
                                    for (float[] feascore : feascoreList) {
//                                        logger.info("input feascore:{}", Arrays.toString(feascore));
                                        Double score = FeatureScoreUtils.getScore(feascore, sourceFeature);
//                                        logger.info("score:{}", score);
                                        minScore = score < minScore ? score : minScore;
                                    }
                                    return minScore;
                                } catch (Exception e) {
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

        private byte[] getDocValues(SearchLookup lookup, LeafReaderContext context, String feature) throws IOException {
            //lookup.source()为es中索引的"_source"中的值
            String features = (String)lookup.source().get(feature);
//            logger.info("features:{}",features);
            return TypeChangeUtils.DecodeBs64String2bytes(features);
        }
    }
}
