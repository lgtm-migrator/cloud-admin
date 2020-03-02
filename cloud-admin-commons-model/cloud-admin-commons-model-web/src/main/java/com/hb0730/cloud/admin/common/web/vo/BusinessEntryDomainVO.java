package com.hb0730.cloud.admin.common.web.vo;

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
public class BusinessEntryDomainVO extends BusinessDomainVO {
    /**
     * 父类id
     */
    private String parentId;
}
