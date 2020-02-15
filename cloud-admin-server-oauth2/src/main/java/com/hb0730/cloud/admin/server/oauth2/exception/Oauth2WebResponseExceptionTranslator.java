package com.hb0730.cloud.admin.server.oauth2.exception;

import com.hb0730.cloud.admin.common.web.utils.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class Oauth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.OK);
        if (e instanceof UnsupportedGrantTypeException) {
            return status.body(ResponseResult.resultFall("不支持该认证类型"));
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                return status.body(ResponseResult.resultFall("refresh token无效"));
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                return status.body(ResponseResult.resultFall("用户已被锁定，请联系管理员"));
            }
            return status.body(ResponseResult.resultFall("用户名或密码错误"));
        }
        return status.body(ResponseResult.resultFall("认证失败"));
    }
}
