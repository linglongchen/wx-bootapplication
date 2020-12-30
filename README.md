# 微信小程序后端接口项目
## 涉及技术

 - SpringBoot
 - MybatisPlus

  

 - Redis

  

 - Mysql

  

 - Swagger2

  

 - JWT

## 介绍
 - 登录：通过微信授权登录，登录的同时生成token发送给前端，前端每次登录在header中传递token
 - Redis：缓存存储，主要实现存储token，设置过期时间（可自己更改做数据缓存）
 - JWT：使用JWT方式生成token，验证用户是否登录成功通过前端传递的token与Redis中存储的token进行对比
 - 微信：整合微信授权以及微信支付功能，可直接配置OpenApi类配置自己的微信小程序appid与secret。


## 展示

![微信图片_20201230103007.png](https://i.loli.net/2020/12/30/c4deKCSsv7hDM1B.png)



## 功能

- 1、实现了基本的登录登出接口，可以直接对接微信小程序。
- 2、其它具体功能根据需求自定义，自动代码生成可以查看common中的generator文件夹

  
该项目长久更新，喜欢的可以start一下，谢谢！

  





