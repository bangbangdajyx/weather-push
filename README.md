# 公众号天气推送

## 项目介绍
SpringBoot 项目（公众号推送早安问候以及天气预报）  
程序员必备撩妹工具，微信自动发送问候语，每天早中晚定时骚扰，集合早安问候语录、渣男语录、图灵机器人笑话。 

## 演示图例
![demo](./img/demo.jpg) 

## 准备工作
1. 微信公众平台接口测试账号申请：https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login   
2. 申请登录之后  
![img1](./img/1.png)  
![img2](./img/2.png)  
![img3](./img/3.png)  
![img4](./img/4.png)  
3. 在 src/main/resources/application.yaml 里面填写配置项信息，端口默认80  

## 模板示例如下
```text
早上好呀小吉的宇宙无敌第一美少女老婆{{remork.DATA}}
今天是：{{date.DATA}}
所在地区：{{city.DATA}}
当前天气：{{weather.DATA}} 
当前温度：{{temp.DATA}} °
最高温度：{{high.DATA}}° 
最低温度：{{low.DATA}}°
今天是我们在一起的第{{loveDays.DATA}}天啦❤ 
今天也是爱老婆的一天呀😘
```

## 关于百度地图开放平台
百度地图开放平台：https://lbsyun.baidu.com/apiconsole/center#/home  
天气服务接口文档：https://lbs.baidu.com/index.php?title=webapi/weather  
创建应用（选择服务端）：https://lbsyun.baidu.com/apiconsole/key#/home  
注意：在 src/main/resources/application.yaml 里面填写"应用AK"和"城市ID/行政编码"，如合肥行政代码是340100

## 关于彩虹屁平台
注册天行数据账号并申请接口：https://www.tianapi.com/  
注意：在 src/main/resources/application.yaml 里面填写"apiKey"  

## 如何运行
启动项目后，打开浏览器输入  http://localhost/test  即可手动推送消息。  
注意：默认设定每天早上8点推送消息，如需修改可去 Task 类上修改 [Cron表达式生成器](https://www.bejson.com/othertools/cron/)  

## 打包发布：关于 SpringBoot 项目编译打包，这里就不用多说了
1. 方法一：通过 IDEA 的方式  
2. 方式二：通过 maven 的方式

## 温馨提示
通常情况下只需要在 src/main/resources/application.yaml 文件中配置信息，不需要修改其它任何代码。
