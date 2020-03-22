FROM openjdk:8-jdk-alpine

MAINTAINER bing_huang <huangbing0730@gmail.com>
# copy arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone

ENV SPRING_PROFILES_ACTIVE="dev" \
    BASE_DIR="/app" \
    SERVER_NAME="cloud-admin-gateway" \
    SKYWALKING_URL="localhost" \
    JAVA_OPTS="-server -Xms2048m -Xmx2048m -Xmn512m -XX:MaxMetaspaceSize=512m"
# app.jar
RUN mkdir /app
ARG JAR_FILE
COPY ${JAR_FILE}  /app/app.jar
# skywalking
ADD agent/ /agent
# script
RUN mkdir /app/bin
ADD docker-entrypoint.sh /app/bin/docker-entrypoint.sh
RUN chmod +x /app/bin/docker-entrypoint.sh

ENTRYPOINT ["bin/docker-entrypoint.sh"]
