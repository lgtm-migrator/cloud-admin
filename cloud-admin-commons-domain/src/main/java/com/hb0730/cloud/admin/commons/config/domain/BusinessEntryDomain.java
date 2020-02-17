package com.hb0730.cloud.admin.commons.config.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

/**
 * <p>
 * 业务单据分录
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
@EqualsAndHashCode
public class BusinessEntryDomain extends BusinessDomain {
    /**
     * 父类id
     */
    @TableField("parent_id")
    private String parentId;
}
