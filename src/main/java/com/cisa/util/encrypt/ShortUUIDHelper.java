package com.cisa.util.encrypt;

import java.util.UUID;

/**
 * 生成22位短UUID
 * 
 * @author Xiaolong.Cisa
 * @version 1.0
 * 
 */
public class ShortUUIDHelper {
    private final static char[] DIGITS64 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".toCharArray();

    /**
     * 获取短UUID
     * 
     * @return string类型的UUID
     */
    public static String getId() {
        UUID u = UUID.randomUUID();
        return toIDString(u.getMostSignificantBits()) + toIDString(u.getLeastSignificantBits());
    }

    private static String toIDString(long l) {
        char[] buf = "00000000000".toCharArray(); // 限定11位长度
        int length = 11;
        long least = 63L; // 0x0000003FL
        do {
            buf[--length] = DIGITS64[(int) (l & least)]; // l & least取低6位
            l >>>= 6; // 无符号的移位只有右移，没有左移, 使用“>>>”进行移位
        } while (l != 0);
        return new String(buf);
    }
}