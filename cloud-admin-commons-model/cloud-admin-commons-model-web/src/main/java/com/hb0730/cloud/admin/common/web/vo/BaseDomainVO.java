package com.hb0730.cloud.admin.common.web.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUserId;
    /**
     * 创建人
     */
    private String createUserName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUserId;
    /**
     * 修改人
     */
    private String updateUserName;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 版本
     */
    private Integer version;
}
