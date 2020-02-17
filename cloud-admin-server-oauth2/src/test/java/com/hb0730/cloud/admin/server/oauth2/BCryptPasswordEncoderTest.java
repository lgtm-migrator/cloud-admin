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
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
        System.out.println(new BCryptPasswordEncoder().matches("1234","$2a$10$T2sJ0IqB4CA/MMjB9Zy3W.O2SIIlQWt8R72I6Kv3R4Mysy6latMm."));
    }
}
