package com.jwk.common.utils;

import java.util.Random;

/**
 * 优惠码生成
 * @author  陈志辉
 */

public class Activatedcode {

    public static String code() {
        Random random = new Random();
        String candicatedCode = "abcedefghijklmnopqrstuvwxyz";//优惠码包含小写字母
        candicatedCode+=candicatedCode.toUpperCase();//优惠码包含大写字母
        candicatedCode+="1234567890";//优惠码包含阿拉伯数字
        String res ="";
        for(int j=0;j<14;j++){
            res+=candicatedCode.charAt(random.nextInt(candicatedCode.length()));
        }
        return res;
    }
}
