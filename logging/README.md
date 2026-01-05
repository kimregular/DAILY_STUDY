# shorten-url-for-logging

## 실행 방법

이 프로젝트는 Elasticsearch, Logstash, 스프링 부트 애플리케이션을 Docker Compose를 통해 한 번에 실행할 수 있도록 구성되어 있습니다.

### 사전 준비
- Docker 및 Docker Compose가 설치되어 있어야 합니다.
- 프로젝트 빌드를 위해 Maven이 필요하거나 `./mvnw`를 사용할 수 있습니다.

### 실행 단계

1. **애플리케이션 빌드**
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. **Docker Compose 실행**
   ```bash
   docker-compose up --build
   ```

### 서비스 정보
- **Spring Boot App**: http://localhost:8080
- **Elasticsearch**: http://localhost:9200
- **Logstash**: http://localhost:9600