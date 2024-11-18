FROM alpine/java:21-jdk
# Uygulamanın JAR dosyasını kopyala
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# Çalışma zamanı komutunu belirle
ENTRYPOINT ["java","-jar","/app.jar"]