package com.hb0730.cloud.admin.server.user.role.system.model.vo;

import com.hb0730.cloud.admin.common.web.vo.BusinessDomainVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemUserRoleVO extends BusinessDomainVO {

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 是否启用
     */
    private Integer isEnabled;

    /**
     * id
     */
    private Long id;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 角色 ID
     */
    private Long roleId;

}
