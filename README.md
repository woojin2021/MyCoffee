# MyCoffee

>부산IT교육센터 블록체인 기반 핀테크 및 보안 플랫폼 개발(자바/리엑터) 과정 팀 프로젝트   
>2022/10/24 ~ 2022/11/10 

## 목적
>3개의 독립된 모듈(고객, 카페, 배달)간의 메세지(주문 상태 정보) 교환 실험   
>push 기능을 구현하기 힘든 web 특성상 ajax를 사용해서 최신상태를 유지

## 참가자
>정우진, 정예찬, 민병도

## 개발 환경 및 사용 기술
- 프론트앤드
	- HTML5, CSS3, JavaScrpit
	- Bootstrap 4.6.2
	- JQuery 3.6.1
	- JSP, Ajax, JSON
- 백앤드
	- JAVA 1.8
	- spring framework 5.2.9.RELEASE
	- MyBatis
- 데이터베이스
	- ORACLE 11g
- 웹서버
	- TOMCAT 9.0
   
## 모듈별 기능
|모듈명|주요기능|담당|
|------|---|---|
|고객|상품주문|민병도|
|카페|상품관리 주문처리|정우진|
|배달|상품배달|정예찬|

## 팀 프로젝트 성과/반성
1. 기존의 Servlet/JSP와 spring framework의 차이점 인식
2. 개인작업으로는 파악할 수 없었던 GitHub 사용시 발생하는 다양한 트러블에 대응
3. Web UI에 대한 지식 재점검
4. 협업에 있어서 커뮤니케이션의 중요성을 다시금 인식


# 개발이력
- v1.0 프로토타입: 일부 기능이 미구현되었으나 팀프로젝트 기간종료로 마무리   
향후 개별적으로 수정
- v1.1 java package, web folder https://github.com/woojin2021/MyCoffee/pull/4#issue-1456737925
- v1.2 배달원 모듈 수정 https://github.com/woojin2021/MyCoffee/pull/7#issue-1459834152
- v1.3 고객 모듈 수정 & 마무리 https://github.com/woojin2021/MyCoffee/pull/8#issue-1467846371

# v2.0 개발안
### 현 프로젝트를 상용화한다면?
1. 배달 모듈을 별도의 시스템으로 분리
2. 웹 서버와 REST API 서버로 분리
