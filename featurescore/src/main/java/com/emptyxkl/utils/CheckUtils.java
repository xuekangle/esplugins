package com.emptyxkl.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckUtils {
    private final Logger LOGGER = LogManager.getLogger(CheckUtils.class);

    public static boolean is_empty(String string){
        boolean flag = false;
        if(string.equals("")||string == null){
            flag = true;
        }
        return flag;
    }

    public static boolean checkFeatureSize_isSame(float[] feascore,byte[] asBytes){
        boolean flag = true;
        int docLen = 0;
        if((asBytes.length/4)>512){
            docLen = asBytes.length-256;
        }else{
            docLen = asBytes.length-128;
        }
        if((docLen/4) != feascore.length){
            //长度不想等，返回false
            flag = false;
        }
        return flag;
    }
}
