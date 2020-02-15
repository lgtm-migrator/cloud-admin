package com.hb0730.cloud.admin.commons.genterator;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
class mysqlProperties {
    static final String MYSQL_URL = "jdbc:mysql://rm-bp14b893npcro13pmlo.mysql.rds.aliyuncs.com/cloud-admin?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8";
    static final String MYSQL_USERNAME = "bing_huang";
    static final String MYSQL_PASSWORD = "HBhuangbing0730";
    static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    static final String[] SUPER_ENTITY_COLUMNS = new String[]{"delete", "enabled"};
    static final String SUPER_CONTROLLER_CLASS="com.hb0730.cloud.admin.common.web.controller.AbstractBaseController";
    static final String SUPER_SERVICE_IMPL_CLASS="com.hb0730.cloud.admin.commons.service.BaseServiceImpl";
}
