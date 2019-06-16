package com.emptyxkl.utils;

import java.util.Base64;

public class TypeChangeUtils {
    public static float[] FeaturebyteArray2floatArray(byte[] featurebytes){
        float feascore[] = new float[featurebytes.length/4];
        for (int i = 0; i < feascore.length; i++) {
            feascore[i] = Utils.byte2float(featurebytes,i*4);
        }
        return feascore;
    }

    public static byte[] DecodeBs64String2bytes(String featureStr){
        byte[] featurebytes = Base64.getDecoder().decode(featureStr);
        return featurebytes;
    }

    public static float[] Bs64String2float(String base64String){
        return FeaturebyteArray2floatArray(DecodeBs64String2bytes(base64String));
    }

    public static float[] Bytes2floats(byte[] byteFeatures){
        return FeaturebyteArray2floatArray(byteFeatures);
    }
}
