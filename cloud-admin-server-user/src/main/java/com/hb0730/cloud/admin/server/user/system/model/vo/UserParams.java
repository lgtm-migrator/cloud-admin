package com.hb0730.cloud.admin.server.user.system.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class UserParams implements Serializable {

    private static final long serialVersionUID = -66L;

    /**
     * 组织id
     */
    private Long deptId;
}
