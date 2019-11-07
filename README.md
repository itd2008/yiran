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
## 技术交流群
- QQ群：908484917
- 微信号:panda726548 加微信，拉进微信群【备注：依然框架交流】
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
```
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
```
## 架构图
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/133143_4621a5b7_928853.png "屏幕截图.png")
## 支付流程
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/133214_5e0b5cba_928853.png "屏幕截图.png")
## 模块功能说明
1.  **用户管理** ：用户是系统操作者，该功能主要完成系统用户配置。对各个基本的用户增删改查，导出excel表格，批量删除。
1.  **角色管理** ：角色菜单权限分配、设置角色按机构进行分配菜单权限和增删改查权限。
1.  **菜单管理** ：N级别自定义菜单，自定义菜单图标，业务菜单和系统菜单分离，菜单状态显示隐藏，配置系统菜单，操作权限，按钮权限标识等。
1.  **部门管理** ：配置系统组织机构（公司、部门、小组），树结构展现。
1.  **岗位管理** ：配置系统用户所属担任职务。
1.  **字典管理** ：对系统中经常使用的一些较为固定的数据进行维护。
1.  **参数管理** ：对系统动态配置常用参数。
1.  **通知公告** ：系统通知公告信息发布维护。
1.  **操作日志** ：系统正常操作日志记录和查询；系统异常信息日志记录1. 查询。
1.  **登录日志** ：系统登录日志记录查询包含登录异常。
1.  **在线用户** ：当前系统中活跃用户状态监控。可强制用户下线。
1.  **定时任务** ：在线（添加、修改、删除)任务调度包含执行结果日志。启动、暂停、执行定时任务操作。
1.  **数据监控** ：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
1.  **服务监控** ：监控服务器相关信息。
1.  **表单构建** ：拖拽式快速构建表单，组建元素丰富，有富文本、上传控件、下拉框等等
1.  **代码生成** ：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。
1.  **系统接口** ：根据业务代码自动生成相关的api接口文档。开发人员只需要加好注解自动生成API接口文档。
1.  **UES加密** ：系统加密模块，对敏感信息加密，提供加密解密方法。
1.  **数据库表结构** ：数据库所有表结构说明
1.  **微信公众号菜单** ：自定义微信公众号菜单，发布菜单到微信公众号，查看已发布微信公众号菜单。
1.  **微信基本设置** ：微信相关的基本设置，如微信APPID、微信秘钥、微信token、微信回复URL、授权后重定向的回调链接地址、微信商户ID.....
1.  **公众号微信用户** ：获取关注微信公众号所有用户。
1.  **微信模板** ：配置公众号推送消息模板。
1.  **商品管理** ：微信商城商品信息发布，商品类目发布，商品品牌配置，商品属性规格添加，优惠券设置，促销活动设置....。
1.  **订单管理** ：订单列表，订单物流，订单评论
1.  **店铺设置** 图标分类，首页栏目设置，首页宣传图片管理：
1.  **收货地址** ：收货地管理。
1.  **购物车** ：微信商城购物车。
1.  **省市区街道** ：省市区街道名称，代码,金纬度..。
1.  **会员管理** ：会员信息管理。
1.  **渠道管理** ：支付渠道管理，包括资金渠道配置（支付渠道），目标机构配置，API结果码设置，统一结果码配置...
1.  **平台订单渠道** ：平台支付订单，所有的交易都走支付核心，所有的交易都记录在渠道订单中。
1.  **综合管理** ：联合查询，根据不同的条件查询订单支付结果，机构订单结构查询，根据机构订单号（提供给第三方的订单号）从第三方支付或者银行查询支付结果。
1.  **交易对账管理** ：每天定时对前一天平台的交易订单和银行方（例如：微信、支付宝...）订单进行匹配校验，校验订单状态、手续费、交易金额等。
1.  **平台订单渠道** ：平台支付订单，所有的交易都走支付核心，所有的交易都记录在渠道订单中。
1.  **MQ管理** ：配置MQ消息，记录发送的MQ消息信息，按照一定的规则处理发送失败的消息数据
1.  **文件管理** ：上传文件，对文件进管理
## 在线体验
```
用户名：test  密码:123456
```
- 演示地址：www.yirantrade.com
- API地址: http://api.yirantrade.com/swagger-ui.html
- 文档地址：https://www.showdoc.cc/507593531047031
- 微信公账号商城体验
- ![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/134819_0609ee7e_928853.png "屏幕截图.png")
## API接口
![输入图片说明](https://images.gitee.com/uploads/images/2019/1107/085300_49bf8325_928853.png "屏幕截图.png")
## 演示图
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/135337_e85b0d12_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/135411_127852ec_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/135647_b04d6c2f_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/140255_f9c426d5_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/140338_acb0a1f4_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/140424_6b8e363e_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/140458_97da2ea8_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/140615_097e8c4b_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/140648_445f80ef_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/142625_4860e86f_928853.png "屏幕截图.png")
## 微信商城演示

![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145229_933a7a54_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145302_994caa06_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145334_381fc3fc_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145409_7f32bf4d_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145605_11ed4552_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145747_660d579f_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145829_c30d8fca_928853.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145859_482ad6da_928853.png "屏幕截图.png")
## 请作者喝咖啡
![输入图片说明](https://images.gitee.com/uploads/images/2019/1106/145932_5eb76f08_928853.png "屏幕截图.png")
