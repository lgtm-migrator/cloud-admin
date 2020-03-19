package com.hb0730.cloud.admin.common.web.utils;

import com.hb0730.cloud.admin.common.util.GsonUtils;
import com.hb0730.cloud.admin.common.web.exception.BeanUtilsException;
import com.hb0730.cloud.admin.common.web.exception.BusinessException;
import com.hb0730.cloud.admin.common.web.response.ResultJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * json转换bean
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class JsonConvertBeanUtils {

    /**
     * <p>
     * 将{@link ResultJson} 转换成bean类型集合
     * </p>
     *
     * @param result {@link ResultJson}
     * @param clazz  目标类型
     * @param <T>    目标类型
     * @return 转换后的类型
     */
    public static <T> List<T> convertList(ResultJson result, Class<T> clazz) {
        if (Objects.isNull(result)) {
            return new ArrayList<T>();
        }
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getStatusCode())) {
            throw new BeanUtilsException(result.getData().toString());
        }
        return convertList(result.getData(), clazz);
    }

    /**
     * <p>
     * 将{@link ResultJson} 转换成bean类型
     * </p>
     *
     * @param result {@link ResultJson}
     * @param clazz  目标类型
     * @param <T>    目标类型
     * @return 转换后的类型
     */
    public static <T> T convert(ResultJson result, Class<T> clazz) {
        if (Objects.isNull(result)) {
            return null;
        }
        if (!CodeStatusEnum.SUCCESS.getCode().equals(result.getStatusCode())) {
            throw new BusinessException(result.getData().toString());
        }
        return convert(result.getData(), clazz);
    }

    /**
     * <p>
     * 将json string类型转换成class list
     * </p>
     *
     * @param t     json Object
     * @param clazz 需要转换成的类型
     * @param <T>   目标类型
     * @return 转换后的集合类型
     */
    public static <T> List<T> convertList(Object t, Class<T> clazz) {
        if (Objects.isNull(t)) {
            return new ArrayList<T>();
        }
        return GsonUtils.json2List(GsonUtils.json2String(t), clazz);
    }

    /**
     * <p>
     * 将json类型转换成bean类型
     * </p>
     *
     * @param t     json string源
     * @param clazz 需要被转换的类型
     * @param <T>   模板类型
     * @return 转换后bean
     */
    public static <T> T convert(Object t, Class<T> clazz) {
        if (Objects.isNull(t)) {
            return null;
        }
        return GsonUtils.json2Bean(GsonUtils.json2String(t), clazz);
    }
}
