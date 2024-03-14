# 基于springboot的微信公众号模板

# TODO
- 完善各类消息的Handler
- ~~将任务触发的配置文件与主配置文件拆分~~
- ~~获取AccessToken~~ 以及 使用AccessToken的各种功能(~~自定义菜单栏~~、群发消息、上传媒体文件)

# 项目结构
```
D:.
├─main
│  ├─java
│  │  └─org
│  │      └─wechat
│  │          ├─component  (服务器的各种依赖)
│  │          │  └─handler (各种消息处理器)
│  │          ├─controller (服务器请求的视图函数)
│  │          ├─mine
│  │          │  ├─task (自定义任务)
│  │          │  └─tool (自定义任务所需的工具)
│  │          └─service (服务器服务)
│  └─resources
│      └─META-INF
└─test
    └─java
```
# 自定义你的公众号
## 自定义任务
- 自定义任务必须继承AbstractTask类 实现getDataList方法
- getDataList返回一个字符串数组
- 字符串数组第一个元素为响应消息的类型
- 字符串数组随后的元素为这个类型的消息的必要参数
- 完成自定义任务函数之后必须在配置文件中填函数触发的正则表达式及该任务函数的所在位置
```
text:
  order:
    - - "^([\\u4e00-\\u9fa5]{2,5}) ([\\u4e00-\\u9fa5]{2,5}) 天气$"
      - "org.wechat.mine.task.WeatherForecast"
    - - "你的任务触发消息"
      - "任务类的包路径"...

event:
    - - "EventKey"
      - "任务类的包路径"
```
## 自定义菜单栏
在自定义菜单栏之前先看看微信公众号的开发文档
[自定义菜单](https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html)

看完后需要按照他们的开发文档编写自己需要的菜单栏json文件 [json路径](src/main/resources/menu.json)
### 部署新的菜单栏
微信公众号重新绑定服务器url即可
### 删除菜单栏
menu.json 中只保留一个`{}`即可







# 运行
运行该java文件 WechatServerApplication.java 或者 以该文件为主类打包项目
`java -jar WechatServer.jar`
