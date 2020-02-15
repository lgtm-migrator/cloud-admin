package com.hb0730.cloud.admin.server.user;

import cn.hutool.core.lang.ObjectId;
import org.junit.Test;

import java.util.Objects;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class Id {

    @Test
    public void getId(){
        System.out.println(ObjectId.next());
    }
}
