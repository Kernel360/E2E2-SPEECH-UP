FROM amazoncorretto:17

LABEL authors="Kangmin Lee"

# 빌드에서 생성된 JAR 파일을 컨테이너의 /docker-springboot.jar로 복사
COPY build/libs/up-0.0.1.jar /docker-springboot.jar

# ENTRYPOINT로 Java 애플리케이션을 실행
ENTRYPOINT ["java", "-jar", "/docker-springboot.jar"]
