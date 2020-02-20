package com.hb0730.cloud.admin.server.oauth2;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class BCryptPasswordEncoderTest {

    @Test
    public void test(){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println(new BCryptPasswordEncoder().matches("123456","$2a$10$gbECvCbd9bCdQ/mhntsjheSWXXfjv.fByf.q/Swd2.BXqHZiF9Ssa"));
    }
}
