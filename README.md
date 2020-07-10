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






  
该项目长久更新，喜欢的可以start一下，谢谢！

  





