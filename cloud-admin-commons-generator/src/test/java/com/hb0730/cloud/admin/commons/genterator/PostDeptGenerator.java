package com.hb0730.cloud.admin.commons.genterator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.hb0730.cloud.admin.commons.domain.BaseDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     岗位组织绑定
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class PostDeptGenerator {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MysqlProperties.MYSQL_URL);
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(MysqlProperties.DRIVER_NAME);
        dsc.setUsername(MysqlProperties.MYSQL_USERNAME);
        dsc.setPassword(MysqlProperties.MYSQL_PASSWORD);
        mpg.setDataSource(dsc);
        //生成策略
        StrategyConfig strategy = new StrategyConfig();
        //表前缀
        strategy.setTablePrefix("t_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //公共字段
        strategy.setSuperEntityColumns(MysqlProperties.SUPER_ENTITY_COLUMNS);
        //自定义继承的Entity类全称，带包名
        strategy.setSuperEntityClass(BaseDomain.class);
        //自定义继承的Controller类全称，带包名
        strategy.setSuperControllerClass(MysqlProperties.SUPER_CONTROLLER_CLASS);
        //自定义继承Service类
        strategy.setSuperServiceImplClass(MysqlProperties.SUPER_SERVICE_IMPL_CLASS);
        //常量字段
        strategy.setEntityColumnConstant(true);
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //Boolean类型字段是否移除is前缀（默认 false）
//        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        //乐观锁属性名称
        strategy.setVersionFieldName("version");
        //逻辑删除属性名称
        strategy.setLogicDeleteFieldName("is_delete");
        //需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude("t_system_post_dept");
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        //配置包名
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.hb0730.cloud.admin.server.post.dept");
        pc.setModuleName("system");
        pc.setEntity("model.entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/cloud-admin-server-post-dept/src/main/java");
        gc.setAuthor("bing_huang");
        gc.setOpen(false);
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/cloud-admin-server-post-dept/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.execute();
    }
}
