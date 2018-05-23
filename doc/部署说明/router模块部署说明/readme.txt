1、JDK采用1.8版本
2、项目SVN路径  svn://192.168.50.234/repos_esuite/eRating60/trunk/src/linekong-erating-router
3、配置文件配置说明：
   将所有配置文件都放入一个目录下（自定义一个目录）
   更改erating_router.conf里面的两个路径值的实际位置
   将其它文件里的相应的值改成相应环境的即可，如rpc服务的地址，端口等
4、启动项目：java -jar XXXXX.jar  erating_router.conf