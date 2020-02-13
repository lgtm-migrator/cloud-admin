package com.hb0730.cloud.admin.commons.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 基类 serviceImpl
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
