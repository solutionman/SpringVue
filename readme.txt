


cd backend/

mvn spring-boot:run

new terminal

cd frontend/

npm run serve

in browser: 
localhost:8081/frontend
user admin
pass 12345678


with exteran tomcats:

for jar version:

mvn clean install
java -jar target/backend.jar

for war version:

mvn clean install

backend.war to tomcat

localhost:8080/backend

cd frontend

npm run serve

npm run serve -- --port 8081

localhost:8081/


or

copy tomcat to tomcatFront, change values:

tomcatFront/conf/server.xml

<Server port="8006" shutdown="SHUTDOWN">

<Connector port="8081" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8444" />

WEB-INF with file web.xml to ROOT and front

npm run build

cd distr

all files to tomcatFront in (8081)

webapps/front

localhost:8081




