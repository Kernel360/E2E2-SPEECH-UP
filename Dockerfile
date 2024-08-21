FROM amazoncorretto:17

LABEL authors="Kangmin Lee"

ARG JAR_FILE=build/libs/up-0.0.1.jar

ADD ${JAR_FILE} docker-springboot.jar

ENTRYPOINT ["java", "-jar", "/docker-springboot.jar", ">", "app.log"]
