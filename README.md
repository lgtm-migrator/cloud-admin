# cloud-admin
spring alibaba cloud admin Template
励志打造基于spring-cloud-alibaba为核心的后台管理模板
## 开发环境
* 操作系统：Windows 10 Enterprise
* 开发工具：Intellij IDEA
* 数据库：MySQL 8.0.16
* Java SDK：Oracle JDK 1.8.152
## 项目管理工具
* 项目构建：Maven + Nexus
* 代码管理：Git + GitHub
* 镜像管理：Docker Registry
## 后台主要技术栈
* 核心框架：Spring Boot + Spring Cloud Alibaba-2.2.0
* ORM框架: mybatis-plus简化mybatis开发
* 数据连接池: KikariCP
* 数据库缓存: Redis
* 单点登录: spring-security-oauth2
* 分布式缓存:Alibaba jetcache
* 分布式系统网关:Spring Cloud Gateway
* 分布式注册中心:Spring Cloud Alibaba Nacos
* 分布式配置中心:Spring Cloud Alibaba Nacos-config
* 分布式熔断降级: Spring Cloud Alibaba Sentinel
* 分布式事务: Spring Cloud Alibaba Seata
* MyBatis 分页插件: PageHelper
## 基础设施

|  服务名称   | 服务端口  | 服务说明  |
|----|----|----|
|nacos|8848|nacos注册中心与配置中心|
|sentinel|8408|限流|
|rocketmq|10909，10911|异步通知(跨服务)|
|seata|8091|分布式事务|
## 模块简介
|模块名称|模块地址|模块说明|端口|
|----|----|----|----|
|cloud-admin-dependencies| |项目依赖|null|
|cloud-admin-commons| |项目公共|null|
|cloud-admin-commons-web| |项目web公共|null|
|cloud-admin-commons-domain| |领域模型|null|
|cloud-admin-commons-service| |公共service|null|
|cloud-admin-commons-generator| |公共逆向工程|null |
|cloud-admin-commons-security-feign-config| |openFeign携带access_token|null|
|cloud-admin-gateway| |系统网关|1010|
|cloud-admin-server-router| |系统路由|1020|
|cloud-admin-server-user| | 系统用户|1030|
|cloud-admin-server-oauth2| |系统用户认证|1040|
|cloud-admin-server-menu| | 系统菜单|1050|
|cloud-admin-server-permission| |系统权限|1060|
|cloud-admin-server-role| |系统角色|1070|

# 如何启动整个项目
 1. 首先启动基础设施(nacos,sentinel,redis,mysql等)
 
 2. 启动cloud-admin-server-router 路由服务
 
 3. 启动 cloud-admin-gateway网关服务
 
 4. 启动cloud-admin-server-user服务(无openFeign)
 5. 启动spring-cloud-server-oauth2 
