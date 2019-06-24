package com.emptyxkl;

import com.emptyxkl.utils.FeatureScoreUtils;
import com.emptyxkl.utils.Utils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Base64;

public class FaceTest {


    /**
     * 计算两位的欧式距离，（后边补零，为了补齐长度）
     */
    @Test
    public void test1(){
        float[] test1 = new float[]{113.67194f,34.752607f,0,0,0,0,0,0};
        float[] test2 = new float[]{113.67194f,34.752616f,0,0,0,0,0,0};
        byte[] source1 = new byte[test1.length*4];
        for(int i=0;i<test1.length;i++){
            int f2b = Float.floatToIntBits(test1[i]);
            for(int j=0;j<4;j++){
                source1[i*4+j] = (byte)(f2b>>(j*8));
            }
        }
        System.out.println("source1:" + Arrays.toString(source1));
        byte[] source2 = new byte[test2.length*4];
        for(int i=0;i<test2.length;i++){
            int f2b = Float.floatToIntBits(test2[i]);
            for(int j=0;j<4;j++){
                source2[i*4+j] = (byte)(f2b>>(j*8));
            }
        }
        System.out.println("source2:" + Arrays.toString(source2));
        String result1 = Base64.getEncoder().encodeToString(source1);
        System.out.println(result1);
        String result2 = Base64.getEncoder().encodeToString(source2);
        System.out.println(result2);

    }

    @Test
    public void test2(){
        String base641 = "CVjjQqsCC0IAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=";
        String featurePic = "CVjjQq4CC0IAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=";
        byte[] bytes1 = Base64.getDecoder().decode(base641);
        byte[] bytes2 = Base64.getDecoder().decode(featurePic);
        System.out.println(Arrays.toString(bytes1));

        System.out.println("bytes1 length:"+bytes1.length);
        System.out.println(Arrays.toString(bytes2));
        System.out.println("bytes2 length:"+bytes2.length);

        float[] floats1 = new float[bytes1.length/4];
        for(int i = 0;i<bytes1.length/4;i++){
            float v = Utils.byte2float(bytes1, i * 4);
            floats1[i] = v;
        }
        System.out.println(Arrays.toString(floats1));

        float[] floats2 = new float[bytes2.length/4];
        for(int i = 0;i<bytes2.length/4;i++){
            float v = Utils.byte2float(bytes2, i * 4);
            floats2[i] = v;
        }
        System.out.println(Arrays.toString(floats2));
        long start = System.currentTimeMillis();
        Double scoreNew = FeatureScoreUtils.getScore(floats2, bytes1);
        System.out.println("time:"+(System.currentTimeMillis()-start));
        System.out.println("bytes1:bytes2 score:"+scoreNew);
    }

}
