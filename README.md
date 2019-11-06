## 平台简介
在RuoYi项目基础上改造，通过多模块的方式整合其他经常被用到的功能模块，特别感谢RuoYi。
基于SpringBoot2.0的开发的系统 易读易懂、界面简洁美观。
具备支付系统通用的支付、对账、清算、账户管理、支付订单管理等功能；
目前已接通微信支付渠道，应用微信公众号商城
在此基础上可二次开发，可以用于所有的Web应用程序，如网站管理后台，网站会员中心，OA、ERP、CRM 、CMS,医疗管理、金融。 
可以做APP，微信公众号，微信小程序的后台，json接口，提供了接口例子，后台也有接口测试模块 .
系统功能完善（用户角色权限），此为框架平台，文档、注释齐全，专门供程序员二次开发 
所有前端后台代码封装过后十分精简易上手，出错概率低。
同时支持移动客户端访问。系统会陆续更新一些实用功能。 
## 核心技术
- 核心框架：Spring Boot
- 权限框架：Apache Shiro
- 模板引擎：thymeleaf
- 持久层框架：MyBatis
- 数据库连接池：Alibaba Druid
- 缓存框架：Redis、EhCache
- 日志管理：LogBack
- 工具类：Apache Commons、HuTools
- 视图框架：Spring MVC
- 定时器：Quartz
- 数据库连接池：Druid
- 日志管理：logback
- 页面交互：基于hplus和inspinia
- 下拉框：bootstrap-select
- 文件上传：Bootstrap File Input
- 文件管理器：CKFinder
- 富文本编辑器：CKEditor
- 通讯技术：webSocket
- 数据库：MySQL
- 分布式文件系统：FastDFS
- 持续集成：Jenkins
## 系统结构
'''
YiRan //依然快速开发平台
|
├── yiran-activiti //工作流模块
|
├── yiran-admin //后台管理模块 单独部署
|
├── yiran-amqp //MQ消息队列模块
|
├── yiran-api //api接口模块 单独部署
|
├── yiran-common //公共基础模块
|
├── yiran-file //文件管理模块
|
├── yiran-framework //系统核心模块
|
├── yiran-generator //代码生成模块
|
├── yiran-license //license模块
|
├── yiran-member //会员管理模块
|
├── yiran-message //消息模块
|
├── yiran-pay-sdk //支付SDK模块
|
├── yiran-paychannel //支付渠道路由模块
|
├── yiran-payorder //支付核心模块
|
├── yiran-quartz //定时任务模块
|
├── yiran-reconciliation //交易对账模块
|
├── yiran-system //系统管理模块
|
├── yiran-wechat //微信商城模块
|
├── yiran-weixin //微信公众号模块
## 架构图
