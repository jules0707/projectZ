# projectZ

-Multi-language, responsive, basic CRUD WebApp

-Multi-modules / environment Maven JAVA project

-Spring Mobile, Security, MVC, Core,...

-Hibernate, MYSQL

-Bootstrap,JQuery,JSP

-Mockito 


in [parent-z]:

build 
$ mvn clean install -Dspring.profiles.active=common,dev -Dlog4j.configuration=log4j.xml  -Pdev

run
$ mvn  tomcat7:run -Dspring.profiles.active=common,dev -Dlog4j.configuration=log4j.xml -Pdev
