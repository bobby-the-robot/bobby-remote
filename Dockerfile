FROM openjdk:11-jre-slim
RUN ./gradlew clean assemble
COPY ./build/libs/*.jar /usr/app/
WORKDIR /usr/app
CMD exec java -Xms128m -Xmx512m -jar *.jar
