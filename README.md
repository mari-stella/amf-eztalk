# 시작하기 AMF-EZTALK

## Base Project 다운로드 및 실행
먼저, 새로운 브라우저 탭을 열고, base project 를 gitpod 로 접속합니다
https://gitpod.io/#https://github.com/msa-school/ddd-petstore-level5-layered

GidPod 내에 터미널을 열고(왼쪽 상단의 햄버거 버튼 > Terminal > New Terminal), 프로젝트가 잘 컴파일 되는지 확인합니다:
```
mvn spring-boot:run
```

## 미션
- JPA를 통한 Database Input/Output Adapter 자동생성
- Spring Data REST 를 통한 REST API Input/Output Adaptor 자동생성

## 미션2
- maria db 를 이용하여 연결:
- maria db server 를 기동하기 (docker)
```
 docker run --name maria -d -p 3306:3306 -e MARIADB_ROOT_PASSWORD=admin  mariadb:latest 
```
e4d5a9f8c01564e1607dfed70714f9eb3985e1d0c3db69a4612e9b20489714b2
- 접속 및 DB 생성
```
> docker exec -it maria /bin/bash

root@251ce07fd6fc:/# mysql --user=root --password=$MARIADB_ROOT_PASSWORD
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 4
Server version: 10.7.3-MariaDB-1:10.7.3+maria~focal mariadb.org binary distribution

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. 
Type '\c' to clear the current input statement.

MariaDB [(none)]> 
MariaDB [(none)]> 
MariaDB [(none)]> create database eztalk;
Query OK, 1 row affected (0.000 sec)

MariaDB [(none)]> 

```
- application.yaml 의 설정
```
spring:
  jpa:
    hibernate:
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
      ddl-auto: update
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        dialect: org.hibernate.dialect.MySQL57Dialect
  datasource:
    url: jdbc:mysql://localhost:3306/eztalk
    username: root
    password: admin
    driverClassName: com.mysql.cj.jdbc.Driver
```
- 데이터 확인
```
MariaDB [(none)]> use eztalk
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
MariaDB [eztalk]> show tables;
+--------------------+
| Tables_in_eztalk   |
+--------------------+
| Member             |
| hibernate_sequence |
+--------------------+
2 rows in set (0.000 sec)

MariaDB [eztalk]> 
MariaDB [eztalk]> 
MariaDB [eztalk]> select * from Member;
+----+--------+-------+----------+------+----------------------------+
| id | active | level | memberId | name | recentActivityDate         |
+----+--------+-------+----------+------+----------------------------+
|  1 |        |     0 | qwerty   | ???  | 2022-04-21 01:10:25.000000 |
+----+--------+-------+----------+------+----------------------------+
1 row in set (0.000 sec)

```

## 다음: 도메인 영역의 확장 (Single Model, Big Ball Of Mud)
- Store 업무 영역의 추가
- 비대한 Monolith
- https://github.com/msa-school/ddd-petstore-level7-big-ball-of-mud