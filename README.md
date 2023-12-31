<div align="center">
  <img src="./Logo/logo-text-nobg.png" alt="Logo" style="width: 50%;">
    <img src="https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183548779.png" alt="Logo" style="width: 100%;">
</div>


## 概要

这是数据库课程设计，小破书  `EasyBook` `EBook` `little broken book` 的专属仓库，存放开发过程中的**各类文档和代码**。

本项目将运用软件工程结构化设计方法逐步设计实现一个图文论坛系统。

项目组：EasyBook Designers

项目成员：@aqpower @zhuxinr @wzbmkbk

## 项目介绍

### 功能设计

1. 用户可使用电子邮件注册账户。注册流程包括设置头像和昵称，并通过验证码验证完成注册。用户须完成注册后方可登录系统。
2. 登录后，用户可选择按时间顺序或颜色分区查看帖子，或通过关键词搜索。同时，用户可查看所关注用户发布的帖子。
3. 用户能发布带图片的帖子，并在发布时选择帖子的颜色分区，例如“奇迹紫”或“活力橙”。系统将敏感词替换为“*”后立即发布，可被其他用户看见。
4. 用户可对帖子进行点赞、收藏操作，亦可取消这些操作。同时，用户可对帖子进行评论，评论内容经过敏感词处理后发布。用户还可对帖子进行举报，系统提供违规选项供用户选择。
5. 每个用户都拥有个人主页，展示基本信息、发布的帖子和评论、点赞、收藏的帖子，以及人际关系，包括关注用户和粉丝的数量和详情。用户可在主页删除帖子、修改个人信息或注销账户。
6. 用户也可在其他用户的主页进行关注或拉黑操作。拉黑后，用户间内容将互不可见。
7. 用户对帖子的点赞、收藏、关注操作将通知帖子发布者，用户可查看通知。
8. 管理员可处理举报，结合帖子内容和举报原因，选择删除帖子或对用户进行封禁或禁言。
9. 管理员可查看帖子点击量、系统总发帖量、今日总发帖量等数据统计，以及系统最受欢迎的头像等信息。
10. 超级管理员可创建管理员，并查看管理员的操作记录。

### 界面设计

- 发现界面

![newpost4](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/newpost4.png)

- 用户主页

![image-20231222183159474](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183159474.png)

- 评论列表

![image-20231222183221953](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183221953.png)

- 发布帖子

![image-20231222183441720](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183441720.png)

- 查看帖子详情

![image-20231222183313729](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183313729.png)

- 系统提供八种风格迥异的主题

![image-20231222183522470](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183522470.png)

- 查看统计数据

![image-20231222183729058](https://jgox-image-1316409677.cos.ap-guangzhou.myqcloud.com/blog/image-20231222183729058.png)

## 进展

2023.11.26 初步需求分析，撰写了三个版本的需求分析

2023.11.27 总结需求分析，增加歌词分享需求，增加颜色分区需求，思考了名字。

2023.11.28 完成任务书撰写

2023.12.4 完成顶层数据流图，开始设计数据字典

2023.12.5 撰写数据字典文档

2023.12.6 

- 复盘完善需求分析
- 开始总体设计，分别完成了系统结构设计和数据设计,数据设计包括ER图和数据库表的设计，系统设计包括体系结构设计和软件结构图的绘制
- 完成`EasyBook`的`Logo`设计

2023.12.7

- 完善ER图，完成数据库表的设计
- 完成建表SQL语句
- 使用Apifox进行接口设计

2023.12.8
- 开始编码

2023.12.9
- 基本完成系统三大模块之一的用户模块
- 实现了邮箱发送验证码
- 实现管理员注册接口

2023.12.10
- 开始实现帖子模块，包括分页获取帖子，发布帖子接口

2023.12.11

- 完成了图片上传接口
- 实现了图文帖子发布接口
- 实现了管理员登录接口

2023.12.22
- 完成课程设计报告书的撰写