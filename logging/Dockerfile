# 1단계: 빌드 스테이지 (Maven이 포함된 이미지를 사용합니다)
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /build

# 빌드에 필요한 설정 파일과 소스 코드 복사
COPY pom.xml .
COPY src src

# Maven을 직접 사용하여 애플리케이션 빌드
RUN mvn clean package -DskipTests

# 2단계: 실행 스테이지
FROM eclipse-temurin:17-jdk
WORKDIR /app

# 빌드 스테이지에서 생성된 JAR 파일만 복사
COPY --from=builder /build/target/*.jar app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app/app.jar"]