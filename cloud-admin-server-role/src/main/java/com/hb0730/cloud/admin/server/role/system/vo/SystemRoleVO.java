package com.hb0730.cloud.admin.server.role.system.vo;

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
public class SystemRoleVO extends BusinessDomainVO {
    /**
     * 是否删除
     */
    private Integer isDelete = 0;

    /**
     * 是否启用
     */
    private Integer isEnabled = 1;

    /**
     * id
     */
    private Long id;

    /**
     * 父角色
     */
    private Long parentId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色英文名称
     */
    private String enname;

    /**
     * 备注
     */
    private String description;
}
