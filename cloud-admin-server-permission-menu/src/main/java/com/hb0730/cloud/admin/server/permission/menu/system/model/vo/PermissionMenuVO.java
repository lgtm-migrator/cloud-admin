package com.hb0730.cloud.admin.server.permission.menu.system.model.vo;

import com.hb0730.cloud.admin.api.feign.menu.model.vo.SystemMenuVO;
import com.hb0730.cloud.admin.commons.model.security.permission.SystemPermissionVO;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 组合
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Data
public class PermissionMenuVO implements Serializable {
    private static final long serialVersionUID = -11658L;
    private SystemPermissionVO systemPermissionVO;
    private SystemMenuVO systemMenuVO;
}
