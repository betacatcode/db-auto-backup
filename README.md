# 数据库自动备份工具

## 简介

    可以根据CRON表达式自动备份本机MySQL、Oracle数据库，后续其余数据库备份等有空开发。

## 环境

1. JDK1.8，需要配置到环境变量。
  
2. MySQL的bin目录，需要配置到环境变量。
  
3. Maven环境。
  
4. Oracle数据库备份需要先创建导出目录，并且授予用户导出权限。样例SQL如下，根据实际情况更改。
  
  ```sql
  create directory dpdata1 as 'd:\dump';
  select * from dba_directories;
  grant read,write on directory dpdata1 to xxng;
  ```
  

## 使用说明

1. git下载仓库， cd到项目目录，`mvn clean package`
  
2. 将target目录下`db-auto-backup-0.0.1-SNAPSHOT.jar`文件复制到需要运行的目录，在同级目录下创建`application.yml`文件，文件内进行如下配置。taskList是任务列表，根据自己需要添加任意多个task。
  

```yml
backup:
  taskList:
    - type: MySQL #数据库类型
      ip: 127.0.0.1 #本机ip
      db: chat #需要备份数据库
      username: root #用户名
      password: 123456 #密码
      location: E:\backup #备份路径
      cron: '*/30 * * * * ?' #cron表达式
      run-at-start-up: N #Y/N 是否程序运行就备份一次
      enable: Y #Y/N 是否启用
    - type: Oracle #数据库类型
      db: orcl #服务名
      username: XXNG #用户名
      password: 123456 #密码
      location: dpdata1 #dump文件夹
      tablespace: XXNG #表空间
      cron: '*/10 * 12 * * ?' #cron表达式
      run-at-start-up: N #Y/N 是否程序运行就备份一次
      enable: Y #Y/N 是否启用
```

3. `java -jar db-auto-backup-0.0.1-SNAPSHOT.jar` 运行程序
