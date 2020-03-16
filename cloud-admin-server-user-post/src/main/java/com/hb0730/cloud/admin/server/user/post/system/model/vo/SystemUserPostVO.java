package com.hb0730.cloud.admin.server.user.post.system.model.vo;

import com.hb0730.cloud.admin.common.web.vo.BusinessDomainVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户岗位
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemUserPostVO extends BusinessDomainVO {

    private static final long serialVersionUID = 1L;

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
     * 用户id
     */
    private Long userId;

    /**
     * 岗位id
     */
    private Long postId;
}
