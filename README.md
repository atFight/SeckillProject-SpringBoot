#  SecKill秒杀系统

这是一个SpringBoot入门级项目

基于SpringBoot构建电商基础秒杀项目，使用SpringBoot快速搭建前后端分离的电商基础秒杀项目。项目中会通过应用领域驱动型的分层模型设计方式去完成用户otp注册、登陆、查看、商品列表、进入商品详情以及倒计时秒杀开始后下单购买的基本流程

**技术栈**：Spring boot + Mybatis + JavaScript

项目根据MVC架构分为3层  Controller（控制层）、 Model（模型层）、 View（视图层）  ，View层跟Controller层交互，每个Controller都有一个Model，Model通过与DAO的交互去操作数据库

![](C:\Users\96374\AppData\Roaming\Typora\typora-user-images\1655298891389.png)

在本设计中，所有的业务逻辑都在Model层进行处理，并且将对数据库的操作都将转换成DAO类再进行处理，并且返回一个Model对象给Controller，Controller再把其转换成ViewObject给前端（Controller可以理解为只是连接前端与服务端的桥梁），其中ViewObject是一些对用户开放的数据，或者说对用户有价值的数据。

Model层共有4部分组成，下面的xxDO均对应着一张数据库表

- ItemModel：商品模型，记录商品信息
  1. ItemDO ：记录商品主要信息
  2. ItemStackDO：商品库存信息

- UserModel：用户模型，记录用户信息
  1. UserDO：记录用户主要信息
  2. UserPasswordDO：用户密码密文信息

- OrderModel：交易模型
  1. OrderDO：记录交易主要信息
  2. SequenceDO：记录交易流水单号信息

- PromoModel：促销模型
  1. PromoDO：记录促销的主要信息



另外还有一些比较有意思的工具类

1.业务异常统一处理框架 ： https://juejin.cn/post/7108716043559763998

> 自定义异常类，简单高效的抛出各类异常，并且统一返回格式

2.使用 Javax Validator 进行属性校验：https://juejin.cn/post/7108724103560822798

>  Validator + Annotation 的方式快速校验数据合法性