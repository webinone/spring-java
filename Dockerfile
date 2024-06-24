FROM amazoncorretto:21-alpine-jdk

ARG envMode
ENV ENV_MODE $envMode

WORKDIR /app

COPY ./build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", \
"-Djava.security.egd=file:/dev/./urandom", \
"-Dspring.profiles.active=${ENV_MODE}", \
"-jar", \
"/app/app.jar"]