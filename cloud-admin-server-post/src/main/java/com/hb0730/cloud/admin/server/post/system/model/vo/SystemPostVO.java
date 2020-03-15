package com.hb0730.cloud.admin.server.post.system.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hb0730.cloud.admin.common.web.vo.BaseDomainVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统岗位
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemPostVO extends BaseDomainVO {

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 岗位编码
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String describe;

}
