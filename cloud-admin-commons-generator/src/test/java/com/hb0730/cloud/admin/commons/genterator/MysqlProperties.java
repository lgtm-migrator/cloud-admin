package com.hb0730.cloud.admin.commons.genterator;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
class MysqlProperties {
    static final String MYSQL_URL = "lcoalhost:3306";
    static final String MYSQL_USERNAME = "root";
    static final String MYSQL_PASSWORD = "Admin123";
    static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    static final String[] SUPER_ENTITY_COLUMNS = new String[]{"delete", "enabled"};
    static final String SUPER_CONTROLLER_CLASS="com.hb0730.cloud.admin.common.web.controller.AbstractBaseController";
    static final String SUPER_SERVICE_IMPL_CLASS="com.hb0730.cloud.admin.commons.service.BaseServiceImpl";
}
