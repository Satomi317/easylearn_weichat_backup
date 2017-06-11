package com.easylearn.comm;


import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yuisama on 2017/6/9.
 */
public class EncryptUtil {

    /**
     * 共有的加密类
     * 屈剑飞
     */
    protected static Logger logger = Logger.getLogger(MessageUtil.class.getClass());
    private static MessageDigest md = null;
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * <pre>
     * 任务：
     * 描述：调用微信支付接口，生成package信息对于url转码
     * 作者：屈剑飞
     * 时间：2015年1月23日下午1:13:19
     * @param str
     * @return
     * returnType：String
     * </pre>
     */
    public static String urlEncode(String str){
        str = URLEncoder.encode(str);
        str.replace("+", "%20");
        return str;
    }

    /**
     * @param encryptStr 传入要加密的参数
     * @return 返回加密的结果
     * returnType：String
     * </pre>
     */
    public static String sha1Encode(String encryptStr){
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(encryptStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            logger.info(e);
        } catch (UnsupportedEncodingException e) {
            logger.info(e);
        }
        byte[] result = md.digest();

        StringBuffer sb = new StringBuffer();
        for (byte b : result) {
            int i = b & 0xff;
            if (i < 0xf) {
                sb.append(0);
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /**
     * 描述：对字符串进行MD5加密
     * @param originString 要加密的参数
     * @return 经过MD5加密的字符串
     * returnType：String
     */
    public static String encodeByMD5(String originString){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString;
            } catch(Exception e){
                logger.error(e);
            }
        }
        return originString;
    }

    /**
     * 描述：转换字节数组为十六进制字符串
     * @return 十六进制字符串
     * returnType：String
     * </pre>
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 描述：将一个字节转化成十六进制形式的字符串
     * @return 转换后的字符串
     * returnType：String
     * </pre>
     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

}