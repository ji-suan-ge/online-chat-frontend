package cn.edu.hfut.backend.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private PasswordUtil() {
    }


    public static String encode(String plainPassword) {

        return encodeSha_256(plainPassword);
    }

    public static boolean checkPassword(String plainPassword, String encodePassword) {

        return encodeSha_256(plainPassword).equals(encodePassword);
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @return str 加密后的报文
     */
    public static String encodeSha_256(String str) {

        MessageDigest messageDigest;
        String encodeStr = "";

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encodeStr;
    }

    /**
     * 将byte转为16进制
     */
    private static String byte2Hex(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();
        String temp;

        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }

        return stringBuilder.toString();
    }
}

