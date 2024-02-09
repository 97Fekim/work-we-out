# work-we-out  
> [API 서버 프로젝트](https://github.com/97Fekim/work-we-out)
> <br>
> [배치 서버 프로젝트](https://github.com/97Fekim/work-we-out_batch)

## 🔘 소개

## 🔘 프로젝트 기간

<details open>
  <summary>🗓️총 소요기간 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2023.12.20 ~ 2024.02.17 → 60일간]</summary>
<br>
  <details open>
    <summary>&nbsp;&nbsp;&nbsp;⏰설계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2023.12.20 ~ 2024.01.05 → 17일간] </summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳아키텍처 설계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2023.12.20 ~ 2023.12.25 → 6일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳UI 설계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2023.12.26 ~ 2023.12.29 → 4일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳DB 설계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2023.12.30 ~ 2024.01.02 → 4일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳어플리케이션 설계&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.03 ~ 2024.01.05 → 3일간]<br>
  </details>

  <details open>
    <summary>&nbsp;&nbsp;&nbsp;⏰개발&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.06 ~ 2024.02.03 → 30일간] </summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳테이블-엔티티 매핑&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.03 ~ 2024.01.04 → 2일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳도메인, 서비스 계층 개발&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.05 ~ 2024.01.15 → 11일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳배치JOB, 스케줄러 개발&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.16 ~ 2024.01.17 → 2일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳인증/인가 환경 구축&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.18 ~ 2024.01.21 → 4일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳API 및 프론트엔드 개발&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.01.22 ~ 2024.02.02 → 12일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳보안취약점 보완&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.02.03 ~ 2024.02.03 → 1일간]<br>
  </details>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏰배포 및 통합테스트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.02.04 ~ 2024.02.17 → 14일간]
</details>

## 🔘 설계

<details open>
  <summary>📚설계서 <a href="https://www.notion.so/d1b2c4c478674c31b41c548e1563d7b2?pvs=4">(이동)</a></summary>
  <br>
  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📗 01. 아키텍처 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-f0fa4554c56e4cdb84fcf2be15b8e157?pvs=4">01. 사용 기술 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-1c9f8037570e45b5bc2cd43de8ef4b0b?pvs=4">02. 인프라 정의</a><br>
  </details>

  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📘 02. UI 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-0b0c459409d5404482d0529bc554148a?pvs=4">01. 페이지 목업</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-9040d2a20321478aab62513728d4b2fd?pvs=4">02. 스토리보드</a><br>
  </details>

  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📙 03. DB 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/01-460cf0b9b3614a5d81bfc5fdfbc603c7?pvs=4">01. 개념 모델링</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/02-e097d6a597ae4c068e079f49056c4636?pvs=4">02. 논리 모델링</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/03-ee880376e45d48ff85cb1665b494c1e7?pvs=4">03. 물리 모델링</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/04-DDL-1a1f8db1c72b492891f0232a8324dc52?pvs=4">04. 최종 DDL</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/05-DDL-6a62edb4d4b449818579f2ed563cb812?pvs=4">05. 초기화 DDL</a><br>
  </details>

  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📕 04. 어플리케이션 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/01-cd64f9a7058246c2b7fff1f1451defc2?pvs=4">01. 인증 및 인가</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/02-API-948250aef1df49e681ab4eb07b9d61d0?pvs=4">02. 화면별 API 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/03-API-da21c364096143228c581b6ef8db9add?pvs=4">03. API 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/04-JOB-e090360c4128408e9c1840aed55ff728?pvs=4">04. 배치 JOB 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜<a href="https://www.notion.so/05-52849ca93a8d4ff3a5be954670852e2a?pvs=4">05. 개발 표준 정의</a><br>
  </details>

</details>

## 🔘 사용 기술

### 프론트엔드 개발
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/tailwindcss-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/chart.js-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=black"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=black">

### 애플리케이션 개발
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/spring data jpa-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/spring batch-6DB33F?style=for-the-badge&logo=..&logoColor=white"> <img src="https://img.shields.io/badge/quartz-007396?style=for-the-badge&logo=quartz&logoColor=white">

### 데이터베이스
<img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">

### 빌드 및 배포
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/windows10-0078D6?style=for-the-badge&logo=windows10&logoColor=white">  <img src="https://img.shields.io/badge/apache http server-D22128?style=for-the-badge&logo=apache&logoColor=black">  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black"> 

### 형상관리
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

## 🔘 데이터 관계 모델
> - domain1 - 회원 도메인
> - domain2 - 운동일지 도메인
> - domain3 - 그룹 도메인
> - domain4 - 운동통계 도메인

![image](https://github.com/97Fekim/work-we-out/assets/81150979/f341835d-f017-42c3-a977-030ef009f589)


## 🔘 인프라 아키텍처
![제목 없는 다이어그램 drawio (1)](https://github.com/97Fekim/work-we-out/assets/81150979/aba71622-1f20-4cc8-8269-ebb58aba83d9)


## 🔘 트러블슈팅 및 배운 점
<details>
  <summary>📒 MPA와 SPA 혼용 설계의 아쉬움</summary>
  <br>
   o <strong>현상</strong> : 프론트 엔드 개발 단계에서 중복되는 컴포넌트와 스크립트가 다수 발생하였다. 하지만 이미 페이지 목업 단계에서 페이지의 갯수와 기능이 정해졌기 때문에, 구조를 재정의하기는 어려웠다. 이로 인해 Copy&Paste로 개발하는 컴포넌트가 많아졌고, 이렇게 개발된 컴포넌트에 수정이 생겼을때 일일이 모두 수정해야 했기 때문에 개발 생산성의 저하를 야기했다.<br><br>
   o <strong>원인</strong> : UI설계 당시 명확한 SPA로 정의하지 않고, MPA와 혼용하여 설계한 탓이 크다. 결과론적이지만 개발이 끝나고 되돌아보니, 레이아웃으로 하나의 페이지만 두고 사용자의 요청이 있을때마다 데이터의 렌더링만 CSR 방식으로 해도 되었을 것 처럼 보였다. <br><br>
   o <strong>해결안</strong> : 앞으로 한 두 개만의 신규 기능이 추가될 예정이라면 기존의 구조를 유지한 채 기능을 추가하는 것이 더 효율적으로 보인다. 하지만 신규 기능이 많이 추가될 것이라는 확신이 든다면, 프론트엔드 소스의 재 개발을 하는 편이 앞으로의 개발 생산성을 가속할 것이라고 생각한다. <br><br>
</details>

<details>
  <summary>📒 프론트엔드 프레임워크의 부재로 인한 개발 생산성 저하</summary>
  <br> 
   o <strong>현상</strong> : 무겁고 복잡한 CSR 스크립트.<br><br>
   o <strong>원인</strong> : 화면 데이터의 렌더링 방식의 90%는 CSR(Client Side Rendaring)방식이었다. 프론트엔트 프레임워크를 사용하지 않고, CSR를 바닐라 Javascript 만으로 구현한 탓에 createElement, setAttribute, appendChild 등의 남발을 하게 되어 스크립트는 복잡하고 무거워졌으며, 기술적인 한계에 부딪혔다.<br><br>
   o <strong>해결안</strong> : 리액트 등의 프론트엔드 프레임워크의 존재 이유를 몸소 체감하게 되었고, 학습의 필요성을 느꼈다. 많은 신규 기능의 추가가 확신된다면, 프론트엔드 소스의 재 설계 및 개발이 필요할 듯 싶다.<br><br>
</details>

<details>
  <summary>📒 잦은 변경/삭제 가 일어나는 PK에 대한 인덱스 설계 미흡</summary>
  <br> 
   o <strong>현상</strong> : "운동일지운동종목" 테이블의 PK이며 Unique Index인 운동일지운동종목ID의 빈번한 수정 및 삭제 발생. 이로 인해 인덱스 컬럼임에도 불구하고 조회 성능의 저하.<br><br>
   o <strong>원인</strong> : "운동일지운동종목: 테이블은 수정 및 삭제가 매우 빈번하게 일어나는 테이블이다. 그 이유는 운동일지수정 트랜잭션이 아래의 처리를 하기 때문이다.<br>
  1) 기존의 운동일지운동종목 cascade all 삭제<br> 
  2) 입력받은 새로운 운동일지운동종목으로 모두 INSERT<br>
  따라서 실제로 "운동일지운동종목" 테이블에 존재하는 row의 갯수보다 인덱스의 양이 비대해져 성능 저하가 발생할 가능성이 높다. <br><br>
   o <strong>해결안</strong> : 테이블 구조 수정 or 인덱스 삭제가 강요된다. 그 이유는 운동일지 수정 트랜잭션의 처리 로직을 바꾸는 것은 쉽지 않기 때문이다.(화면에서 테이블의 PK를 추적하기 쉽지 않음) 따라서 고안한 해결 방안은 아래의 두 가지 방법이다.<br>
  1) 테이블에 del_yn 컬럼을 두고, row의 삭제가 일어날때 논리적 삭제처리만 하여 불필요한 인덱스 row 증가를 방지한다.<br>
  2) PK에 대한 인덱스를 삭제처리한 후, 다른 컬럼의 조합으로 Unique 인덱스를 생성한다. <br><br>
</details>

<details>
  <summary>📒 개념 모델링 단계에서 놓친 엔티티의 데이터 관리 난해</summary>
  <br> 
   o <strong>현상</strong> : 개인운동캘린더에서 보여지는 [운동부위 컴포넌트의 색깔] 그리고 그룹운동캘린더에서 보여지는 [회원 컴포넌트의 색깔] 이 두 가지 매핑 정보는 DB가 아닌, 화면 스크립트에서 Map으로 관리되고 있다.<br><br>
   o <strong>원인</strong> : "색깔 정보"의 관리 방식을 개념 모델링 단계에서 명확하게 정의하지 않은 탓이다. 색깔에 관련된 정보는 랜덤으로 부여되기 보다, 명확하게 정해져 있어야 한다. 하지만 이를 스크립트에서 관리하게 될 경우 일관성을 보장하기 위해서는 스크립트를 주기적으로 수정해야 하는 필요성이 생긴다.<br><br>
   o <strong>해결안</strong> : DB설계의 변경은 다른 무엇보다 사이드 이펙트가 크기 때문에, 개념 모델링 단계에서 명확한 엔티티 식별의 중요성을 다시 한번 느낀다. 그럼에도 고안안 해결안은 아래와 같다.<br><br>
  1) 색깔 정보 마스터 테이블의 추가<br>
  2) 운동부위-색깔 정보 릴레이션 테이블의 추가<br>
  3) 회원-색깔 정보 릴레이션 테이블의 추가<br>
  <br>
</details>

<details>
  <summary>📒 Native Query 강요에 의한 JPA의 DBMS 종속</summary>
  <br> 
   o <strong>현상</strong> : JPQL이 아닌 Native Query로 작성된 SQL로 인한, DBMS의 전환 시 중복 개발 발생.<br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> : <br><br>
</details>

<details>
  <summary>📒 Stateless 세션 정책에 의해, 모든 Ajax 요청의 인증 실패</summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> : <br><br>
</details>

<details>
  <summary>📒 다른 서버와 요청간 CORS 정책 위반</summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> : <br><br>
</details>

<details>
  <summary>📒 JpaPagingItemReader의 N+1 문제</summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> : <br><br>
</details>

<details>
  <summary>📒 다중서버 환경에서 인증/인가 메커니즘의 이해</summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> : <br><br>
</details>

<details>
  <summary>📒 파라미터 변조 취약점 및 방어의 이해</summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> : <br><br>
</details>
