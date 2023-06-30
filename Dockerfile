FROM openjdk:17-alpine
VOLUME /tmp
RUN mkdir /work
COPY . /work
WORKDIR /work
RUN /work/gradlew build
RUN mv /work/build/libs/itg.cep-0.0.1-SNAPSHOT.jar /work/app.jar
ENTRYPOINT ["java","-jar","/work/app.jar"]