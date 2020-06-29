package com.emptyxkl.featurescoretype;

import com.emptyxkl.utils.FeatureScoreUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.index.fielddata.ScriptDocValues;
import org.elasticsearch.script.SearchScript;

import java.util.List;

public class FeatureScoreAbstractScript extends SearchScript {

    private final static Logger LOGGER = LogManager.getLogger(FeatureScoreAbstractScript.class);

    protected List<float[]> feascoreList;

    protected int count = 0;

    protected void log(String info){
        if(count++ < 1000){
            LOGGER.info(count + "-" + info);
        }
    }

    public FeatureScoreAbstractScript(List<float[]> feascoreList){
        this.feascoreList = feascoreList;
    }

    @Override
    public double runAsDouble() {
        try{
            //because use doc_values to get binary type has some redundancy data, so this method to delete last 128 bytes
            byte[] sourceFeature = getDocValues("feature");
            Double minScore = Double.MAX_VALUE;
//            byte[] sourceFeature = getDecodeSourceFeature("feature");
            for (float[] feascore:feascoreList) {
//                return FeatureScoreUtils.getScore(feascore,sourceFeature);
                Double score = FeatureScoreUtils.getScore(feascore, sourceFeature);
                minScore = score < minScore ? score : minScore;
            }

            return minScore;
        }catch (Exception e){
//            e.printStackTrace();
            return Double.MAX_VALUE;
        }
    }

    private byte[] getDocValues(String feature) {
        final ScriptDocValues.BytesRefs bytesRefs = (ScriptDocValues.BytesRefs)doc().get(feature);
        //return getDocValues(bytesRefs.getValue().bytes);
        return bytesRefs.getValue().bytes;
    }

}
