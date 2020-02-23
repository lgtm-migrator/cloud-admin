package com.hb0730.cloud.admin.server.router.system.model.vo;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 请求参数
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
public class RouterParamsVO implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * uri
     */
    private String uri;
    /**
     * 说明
     */
    private String description;
}
