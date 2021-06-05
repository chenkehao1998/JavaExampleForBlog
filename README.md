# 博客中的一些例子的代码

写博客记录的时候 有一些小demo放在里面 主要是为了翻看博客的时候 可以有具体的代码来参考

## 如何下载单个例子 或者某些个别文件？

这个git项目中存在着多个不相关的案例，如何将他们使用Git单独下载下来呢？

如果电脑里安装了git可以参考以下操作,以下载spring_aop_xml这个项目为例

1. 在本地新建一个文件夹
2. 在刚刚建好的文件夹中输入命令

```git
$ git init
$ git remote add origin https://github.com/chenkehao1998/JavaExampleForBlog.git
$ git config core.sparsecheckout true
$ echo spring_aop_xml >> .git/info/sparse-checkout
$ git pull origin main --depth=1
```

如果没有git可以使用这个网站[http://blog.luckly-mjw.cn/tool-show/github-directory-downloader/index.html](http://blog.luckly-mjw.cn/tool-show/github-directory-downloader/index.html)

## 每个小demo的介绍

|                           文件夹名                           |                   介绍                   |
| :----------------------------------------------------------: | :--------------------------------------: |
| [dubbo-helloworld-demo](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/dubbo-helloworld-demo) | dubbo使用的简单案例（不使用注解的方式）  |
| [dubbo-helloworld-demo-anno](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/dubbo-helloworld-demo-anno) |  dubbo使用的简单案例（使用注解的方式）   |
| [kehao_spring_aop_transactional_anno](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/kehao_spring_aop_transactional_anno) |  spring aop配置事务 （使用注解的方式）   |
| [kehao_spring_aop_transactional_xml](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/kehao_spring_aop_transactional_xml) | spring aop配置事务 （使用xml配置的方式） |
| [shiro_study](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/shiro_study) |         shiro权限框架的学习demo          |
| [spring_aop_anno](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/spring_aop_anno) |       spring aop面向切面的注解配置       |
| [spring_aop_xml](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/spring_aop_xml) |       spring aop面向切面的xml配置        |
| [spring_mybatis](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/spring_mybatis) |           spring和mybatis整合            |
| [zookeeper_study](https://github.com/chenkehao1998/JavaExampleForBlog/tree/main/zookeeper_study) |         zookeeper的学习代码demo          |



## 我的博客

[克豪的博客_CSDN博客](https://blog.csdn.net/qq754772661)