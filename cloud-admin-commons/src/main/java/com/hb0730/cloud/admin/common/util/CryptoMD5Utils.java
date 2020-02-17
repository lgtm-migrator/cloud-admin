package com.hb0730.cloud.admin.common.util;

import cn.hutool.crypto.digest.MD5;
import org.springframework.lang.NonNull;

/**
 * <p>
 * md5加密
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class CryptoMD5Utils {

    private CryptoMD5Utils() {

    }

    /**
     * <p>
     * 盐值md5加密
     * </p>
     *
     * @param password 密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    public static String encode(@NonNull String password, @NonNull String salt) {
        MD5 md5 = MD5.create();
        md5.setSalt(salt.getBytes());
        return md5.digestHex(password);
    }

    /**
     * <p>
     * 判断密码相等
     * </p>
     *
     * @param password       文明
     * @param salt           盐值
     * @param encodePassword {@link CryptoMD5Utils#encode(String, String)}加密后的密码
     * @return 是否相等
     */
    public static boolean matches(@NonNull String password, @NonNull String salt, @NonNull String encodePassword) {
        String encode = encode(password, salt);
        return encode.equals(encodePassword);
    }
}
