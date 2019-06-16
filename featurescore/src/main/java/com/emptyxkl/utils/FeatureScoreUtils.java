package com.emptyxkl.utils;

import java.math.BigDecimal;

public class FeatureScoreUtils {
    public static Double getScore(float[] feascore,byte[] asBytes){
        double dst = 0;
        for (int i = 0; i < feascore.length; i += 8) {
            //int si = (i + 2) * 4;//skip header
            int si  = i * 4;
            dst += Math.pow((Utils.byte2float(asBytes,si) - feascore[i]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 4) - feascore[i + 1]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 8) - feascore[i + 2]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 12) - feascore[i + 3]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 16) - feascore[i + 4]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 20) - feascore[i + 5]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 24) - feascore[i + 6]),2);
            dst += Math.pow((Utils.byte2float(asBytes,si + 28) - feascore[i + 7]),2);
        }
        return Math.sqrt(dst);
    }

    public static Double getCosScore(float[] feascore,byte[] asBytes){
        double dst = 0;
        double num = 0;
        double denoma = 0;
        double denomb = 0;
        for (int i = 0; i < feascore.length; i += 8) {
            //int si = (i + 2) * 4; //skip header
            int si = i * 4;
            num += multiply(Utils.byte2float(asBytes,si),feascore[i]);
            num += multiply(Utils.byte2float(asBytes,si + 4),feascore[i + 1]);
            num += multiply(Utils.byte2float(asBytes,si + 8),feascore[i + 2]);
            num += multiply(Utils.byte2float(asBytes,si + 12),feascore[i + 3]);
            num += multiply(Utils.byte2float(asBytes,si + 16),feascore[i + 4]);
            num += multiply(Utils.byte2float(asBytes,si + 20),feascore[i + 5]);
            num += multiply(Utils.byte2float(asBytes,si + 24),feascore[i + 6]);
            num += multiply(Utils.byte2float(asBytes,si + 28),feascore[i + 7]);

            denoma += Math.pow(Utils.byte2float(asBytes,si),2);
            denoma += Math.pow(Utils.byte2float(asBytes,si + 4),2);
            denoma += Math.pow(Utils.byte2float(asBytes,si + 8),2);
            denoma += Math.pow(Utils.byte2float(asBytes,si + 12),2);
            denoma += Math.pow(Utils.byte2float(asBytes,si + 16),2);
            denoma += Math.pow(Utils.byte2float(asBytes,si + 20),2);
            denoma += Math.pow(Utils.byte2float(asBytes,si + 24),2);

            denomb += Math.pow(feascore[i],2);
            denomb += Math.pow(feascore[i + 1],2);
            denomb += Math.pow(feascore[i + 2],2);
            denomb += Math.pow(feascore[i + 3],2);
            denomb += Math.pow(feascore[i + 4],2);
            denomb += Math.pow(feascore[i + 5],2);
            denomb += Math.pow(feascore[i + 6],2);
            denomb += Math.pow(feascore[i + 7],2);
        }
        dst = num/(Math.sqrt(denoma)*Math.sqrt(denomb));
        return 1-dst;
    }

    public static float multiply(float a,float b){
        BigDecimal b1 = new BigDecimal(a + "");
        BigDecimal b2 = new BigDecimal(b + "");
        float f = b1.multiply(b2).floatValue();
        return f;
    }
}
