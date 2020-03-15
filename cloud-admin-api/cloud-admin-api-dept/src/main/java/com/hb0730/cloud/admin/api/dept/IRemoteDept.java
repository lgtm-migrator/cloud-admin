package com.hb0730.cloud.admin.api.dept;

import com.hb0730.cloud.admin.common.web.response.ResultJson;

import javax.xml.transform.Result;
import java.util.List;

/**
 * <p>
 * 远程组织接口
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public interface IRemoteDept {
    /**
     * 根据id获取组织信息
     *
     * @param ids id
     * @return 组织信息
     */
    ResultJson getDeptByIds(List<Long> ids);
}
