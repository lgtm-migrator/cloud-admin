package com.hb0730.cloud.admin.server.menu.system.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class VueModelVO implements Serializable {
    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 路径
     */
    private String path;
    /**
     * 子集
     */
    private List<VueModelVO> children;
}
