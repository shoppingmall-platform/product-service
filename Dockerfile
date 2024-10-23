# 1단계: 빌드 단계 (Build stage)
FROM amd64/openjdk:17-jdk-alpine AS build

WORKDIR /app

# Gradle 파일 복사
COPY build.gradle settings.gradle gradlew gradle/wrapper/ ./

# 소스 코드 복사
COPY src ./src

# Gradle 빌드 실행
RUN chmod +x gradlew
RUN ./gradlew clean build

# 2단계: 실행 단계 (Runtime stage)
FROM amd64/openjdk:17-jdk-alpine

WORKDIR /app

# 빌드 스테이지에서 생성된 jar 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 8090 열기
EXPOSE 8090

# 환경 변수를 런타임 이미지에 설정
ENV SPRING_PROFILES_ACTIVE=dev

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]
