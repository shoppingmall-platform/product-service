# 런타임 이미지 준비
FROM amd64/openjdk:17-jdk-alpine

WORKDIR /app

# Gradle 파일 복사
COPY build.gradle settings.gradle gradlew gradle/ ./

# 소스 코드 복사
COPY src ./src

# Gradle 빌드 실행
RUN chmod +x gradlew
RUN ./gradlew clean build

# 빌드 단계에서 생성된 jar 파일 복사
COPY --from=build /app/target/*.jar app.jar

# 포트 8090 열기
EXPOSE 8090

# 환경 변수를 런타임 이미지에 설정
# Set the active Spring profile
ENV SPRING_PROFILES_ACTIVE=dev
#ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
#ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
#ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]