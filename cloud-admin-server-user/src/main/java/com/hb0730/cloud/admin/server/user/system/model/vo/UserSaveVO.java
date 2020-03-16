package com.hb0730.cloud.admin.server.user.system.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSaveVO extends SystemUserVO {
    /**
     * 组织id
     */
    Long deptId;
    /**
     * 岗位id
     */
    List<Long> posts;
    /**
     * 角色id
     */
    List<Long> roles;
}
