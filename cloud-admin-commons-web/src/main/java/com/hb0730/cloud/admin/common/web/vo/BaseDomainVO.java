package com.hb0730.cloud.admin.common.web.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseDomainVO implements Serializable {
    private static final long serialVersionUID = -4454759551309490L;
    /**
     * 创建人
     */
    private Integer createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private Integer updateUserId;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 版本
     */
    private Integer version;
}
