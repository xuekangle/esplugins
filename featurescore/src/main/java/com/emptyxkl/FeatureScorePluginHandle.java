package com.emptyxkl;

import com.emptyxkl.featurescoretype.FeatureScoreAbstractScript;
import com.emptyxkl.utils.TypeChangeUtils;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.xcontent.support.XContentMapValues;
import org.elasticsearch.script.ExecutableScript;
import org.elasticsearch.script.NativeScriptFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeatureScorePluginHandle implements NativeScriptFactory {

    @Override
    public ExecutableScript newScript(@Nullable Map<String, Object> map) {
        String featureStr = (map == null?null: XContentMapValues.nodeStringValue(map.get("feature"),null));

        String[] features = featureStr.split(",");

        if(features == null || features.length == 0){
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

        return new FeatureScoreAbstractScript(feascoreList);
    }

    @Override
    public boolean needsScores() {
        return false;
    }

    @Override
    public String getName() {
        return "featurescore";
    }
}
