FROM openjdk:8-jre-alpine

WORKDIR /user-api/

ADD target/user-api-0.0.1-SNAPSHOT.jar user-api.jar
ADD target/swagger.json swagger/swagger.json

ENTRYPOINT java -Drun.jvmArguments=$JAVA_OPTS -jar user-api.jar
