FROM amazoncorretto:17
ARG JAR_PATH=./build/libs
ARG JAR_FILE=work-we-out-0.0.1-SNAPSHOT.jar
COPY ${JAR_PATH}/${JAR_FILE} ${JAR_PATH}/work-we-out-102.jar
ENTRYPOINT ["java","-jar","./build/libs/work-we-out-102.jar"]