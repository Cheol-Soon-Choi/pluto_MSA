## 0. 요약
- [MSA 맛보기 세부설명(Notion)으로 이동](https://diagnostic-octopus-3df.notion.site/MSA-32a5da497c95466ea714120cab3a7e20)

## 1. 목적         
- [모놀로식 쇼핑몰 서비스](https://github.com/Cheol-Soon-Choi/pluto)를 마이크로 서비스로 분리
- 마이크로 서비스에 사용되는 중요 기술 파악
- 모놀로식 서비스 대비 마이크로 서비스의 장단점 확인

## 2. 빠른 시작 
|순서|내용|명령|
|:---:|:---:|:---:|
|1|프로젝트 빌드|./gradlew clean build|
|2|프로젝트 시작|docker-compose -f docker/docker-compose.yml up --build|
|3|프로젝트 종료|docker-compose -f docker/docker-compose.yml down --rmi all|
        
## 3. 주요 기능
- 컨피그 서버를 통한 구성 관리
- 유레카 서버를 통한 서비스 디스커버리
- 히스트릭스를 통한 회복성 패턴(회로 차단, 폴백, 벌크헤드)
- 주울 서버를 통한 서비스 게이트웨이(라우팅, 필터)
- 인증 서버를 통한 JWT 보안
- Kafka를 통한 비동기 메시지
- 집킨을 통한 분산 추적
- 도커 및 도커 컴포즈를 통한 마이크로 서비스 빌드

## 4. 주요 로직
- Member 서버
  - 회원 CRUD 수행
- Item 서버
  - 제품 CRUD 수행
- Order 서버
  - 주문 CRUD 수행
  - 주문 조회시 Member 서버 및 Item 서버 호출을 통한 주문 정보 제공

## 4. 기능 확인
- Postman을 통한 기능 확인 
  
|순서|내용|설명|주소|
|:---:|:---:|:---:|:---:|
|1|준비|빠른 시작 1~2 실시|-|
|2|JWT 토큰 획득|Method: POST,</br>Authorization-{Type: Basic Auth, Username: kore, Password: pluto},</br>Body-{grant_type: password, scope: webclient, username: ccs or test, password: ccs or test}|`localhost:5555/authserver/auth/oauth/token`|
|3|권한 확인|- 생략 가능 -</br>ccs권한: ROLE_USER, ROLE_ADMIN</br>test권한: ROLE_USER|`localhost:5555/authserver/auth/user`|
|4|JWT 토큰 전달|Headers-{Authorization: bearer 획득한 토큰}|서비스 API 호출|

## 5. 개발 환경 및 사용 기술
- JAVA 8
- Spring boot 2.3.7 (Gradle)
- Spring Cloud(Config, Stream, Slueth, Security)
- Netflix(Zuul, Eureka, Histrix)
- Kafka
- Zipkin
- Docker, Docker Compose
- Intellij ULTIMATE
