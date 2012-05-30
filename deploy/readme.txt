#部署安装说明
# 1. 所有工具由手工安装， 
# 2. 代码更新， 编译， 部署由ant脚本完成，详细步骤请参加ufp-publisher/build.xml。命令： ant -f build_production.xml deploy-production

# Resin 服务器部署到UNODE10(MASTER, 211.151.139.230) 和UNODE11(SLAVE, 211.151.139.2?)上。
Resin 可执行文件放在/home/local/下, resin使用用户"deploy" 运行。
/home/local
|-- resin ->resin-4.0.24 (soft link)
|   |-- webapps
|   	|-- ROOT -> /home/deploy/apps/ufp-deploy/20111201/ (soft link to build.)
|-- resin-4.0.24
|-- resin-4.0.23
|-- ***

UFP 的源代码和编译后的文件放在/home/deploy/apps
/home/deploy/apps
|-- ufp-publisher  (source code)
|-- umeng-core   (source code)
|-- ufp-deploy     (binaries)
|   |-- 20111201    (timestamp based build)
|   |-- 20120101
|   |-- **
|   |-- ***


# 数据库使用UNODE18(211.151.139.206)上的MySQL。数据库用户为"ufp"， 数据库为"ufp", "ufp_log", 手工部署。


#UNODE10 和UNODE11需要安装的工具 (手工安装)

Sun Java JDK
ant
git
Resin

安装脚本
#1. Sun Java JDK
   sudo apt-get install python-software-properties
   sudo add-apt-repository ppa:ferramroberto/java
   sudo apt-get update
   sudo apt-get -y install sun-java6-jdk sun-java6-plugin  
   $ vi $HOME/.bash_profile
   # Append following line:
   export JAVA_HOME=/usr/lib/jvm/java-6-sun
   export PATH=$PATH:$JAVA_HOME/bin
   logout 
   #log out and log back to enable the enviroment variable. 

#2. 安装Mysql 数据库
    sudo apt-get -y install mysql-server

#3. 安装Resin 服务器    
#   Resin详细安装手册在： http://caucho.com/resin-4.0/admin/starting-resin-install.xtp
    cd /usr/local
#    先下载 
     sudo   wget http://www.caucho.com/download/resin-4.0.24.tar.gz
#    解压
    sudo tar -zxvf resin-4.0.24.tar.gz 
#   Soft link Resin
    sudo ln -s ./resin-4.0.24 ./resin
    sudo chown -R deploy:deploy /usr/local/resin
    
#4. 安装ant
    sudo apt-get -y install ant ant-optional
#5. 安装 git 
    sudo apt-get -y install git-core

#6. Clone code
    cd /home/deploy/apps
    ln -s /usr/local/resin
    git clone ssh://deploy@c.umeng.com:29418/ufp/umeng-core.git   
    git clone ssh://deploy@c.umeng.com:29418/ufp/ufp-publisher.git 

#7. copy config file from 230
folder:	/home/deploy/apps/ufp-deploy/shared

#部署脚本 UNODE10 和UNODE11




cd /home/deploy/apps/umeng-core
git pull
ant 


cd /home/deploy/apps/ufp-publisher
git pull
ant deploy-production












------------------------endnote-------------------------------

# net usershare add -l ufp /home/liutong/apps/ufp-publisher/WebContent/ "share for jsp edit" Everyone:f guest_ok=y
# scp -P 18021 build_production.xml deploy@211.151.139.230:~/

