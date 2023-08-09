FROM maven:3.8.3-openjdk-17 as MAVEN

COPY . .

RUN mvn clean package

FROM bellsoft/liberica-openjdk-alpine:17.0.7 as builder

COPY --from=MAVEN ./target/*.jar /application.jar

RUN java -Djarmode=layertools -jar application.jar extract

FROM bellsoft/liberica-openjdk-alpine:17.0.7
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]