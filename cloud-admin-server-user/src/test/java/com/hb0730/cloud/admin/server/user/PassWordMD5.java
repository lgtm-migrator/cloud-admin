package com.hb0730.cloud.admin.server.user;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.crypto.digest.MD5;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class PassWordMD5 {

    public static void main(String[] args) {
        //生成salt
        String next = ObjectId.next();

        MD5 md5 = MD5.create();
        md5.setSalt(next.getBytes());
        System.out.println(md5.digestHex("HB0730"));

        md5.setSalt(next.getBytes());
        System.out.println(md5.digestHex("HB0730"));

        md5.setSalt(next.getBytes());
        md5.setDigestCount(5);
        System.out.println(md5.digestHex("HB0730"));

        md5.setSalt(next.getBytes());
        md5.setDigestCount(6);
        System.out.println(md5.digestHex("HB0730"));
    }
}
