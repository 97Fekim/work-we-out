# work-we-out  
> - [API 서버 프로젝트](https://github.com/97Fekim/work-we-out)
> - [배치 서버 프로젝트](https://github.com/97Fekim/work-we-out_batch)

## 🔘 목차
> ### 1. 소개
> ### 2. 프로젝트 기간
> ### 3. 설계
> ### 4. 사용 기술
> ### 5. 인프라 아키텍처
> ### 6. 트러블슈팅 및 배운점

## 🔘 소개
<details close>
  <summary><strong>(소개 이미지 펼치기)</strong></summary>

### 📆 1st. 날짜별 운동부위를 요약한 운동캘린더를 제공합니다. 
> - 칼같은 운동루틴을 지키는 "헬창"유저들에게 편의성을 제공합니다.
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/85c4e0ad-f06e-4466-bd1b-80ce132d1a70" width="324px;" height="550px;" />
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/427a2174-7836-4e48-867b-0ff69e4e88be" width="324px;" height="550px;" />

### 📆 2nd. 운동을 공유하는 그룹운동캘린더를 제공합니다.
> - 그룹의 멤버간 <strong>운동스케줄 공유</strong>, 동기부여를 도모합니다.
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/bbaa7caf-0ef1-4ee4-970b-f7a84c75d022" width="324px;" height="550px;" />
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/99928707-9f61-49b8-bb3d-f4e5898a63c7" width="320px;" height="550px;" />

### 📊 3rd. 운동 통계 서비스를 제공합니다.
> - <strong>[내 운동통계] , [우리 그룹의 운동통계]</strong> 자료를 확인 할 수 있습니다.<br>
> - [<strong>주간</strong> 운동통계] , [<strong>월간</strong> 운동통계] 모두 한 눈에 확인 가능합니다.
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/89bfff54-9a9a-4fd6-8bfc-fd1eddc71101" width="320px;" height="550px;" />
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/546e9c34-6b9d-4b41-a712-28b5ac173bbf" width="320px;" height="550px;" />

### 📩 4th. 내 운동통계 자료를 문자로 받는 기능을 제공합니다.
> - 직접 조회하기 바쁜 유저를 위해, 주간/월간 운동통계 자료를 <strong>정기적으로 발송합니다.</strong><br>
> - 문자 수신여부는 <strong>선택이 가능</strong>합니다.
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/29a1b4fb-b6d3-466c-985f-e85124a9b1f2" width="320px;" height="550px;" />
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/11535d05-3dbe-47d3-8150-6d80b69da85e" width="320px;" height="550px;" />

### 🛠 5th. 문자 발송이 실패한 자료를 재발송 할 수 있습니다.
> - 프로그램 <strong>관리자에 한하여 이용 가능합니다.</strong><br>
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/6b029938-dd39-47d0-93a9-c952fab92184" width="320px;" height="550px;" />
<img src="https://github.com/97Fekim/work-we-out/assets/81150979/7c57ed77-5969-436b-9754-4179cb75ef91" width="320px;" height="550px;" />
</details>  

## 🔘 프로젝트 기간
 
<details open>
  <summary>🗓️총 소요기간 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2023.12.20 ~ 2024.03.02 → 74일간]</summary>
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
  
  <details open>
    <summary>&nbsp;&nbsp;&nbsp;⏰배포 및 통합테스트&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.02.04 ~ yyyy.mm.dd → n일간] </summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳초기 운영서버 구축&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[2024.02.04 ~ 2024.02.17 → 14일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳운영 서버 컨테이너 전환&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;[2024.02.18 ~ 2024.02.24 → 7일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳CI/CD 환경 구축&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;[2024.02.25 ~ 2024.03.02 → 7일간]<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;⏳클라우드 마이그레이션&nbsp&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;[2024.07.21 ~ yyyy.mm.dd → n일간]<br>    
  </details>
</details>

## 🔘 설계

<details open>
  <summary>📚설계서 <a href="https://www.notion.so/d1b2c4c478674c31b41c548e1563d7b2?pvs=4">(이동)</a></summary>
  <br>
  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📗 01. 아키텍처 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-f0fa4554c56e4cdb84fcf2be15b8e157?pvs=4">01. 사용 기술 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-1c9f8037570e45b5bc2cd43de8ef4b0b?pvs=4">02. 인프라 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/03-1577a228b9aa4f3cab5404ece06def85?pvs=4">03. 클라우드 이행 사양 정리</a><br>
  </details>

  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📘 02. UI 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-0b0c459409d5404482d0529bc554148a?pvs=4">01. 페이지 목업</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-9040d2a20321478aab62513728d4b2fd?pvs=4">02. 스토리보드</a><br>
  </details>

  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📙 03. DB 설계</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-460cf0b9b3614a5d81bfc5fdfbc603c7?pvs=4">01. 개념 모델링</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-e097d6a597ae4c068e079f49056c4636?pvs=4">02. 논리 모델링</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/03-ee880376e45d48ff85cb1665b494c1e7?pvs=4">03. 물리 모델링</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/04-DDL-1a1f8db1c72b492891f0232a8324dc52?pvs=4">04. 최종 DDL</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/05-DDL-6a62edb4d4b449818579f2ed563cb812?pvs=4">05. 초기화 DDL</a><br>
  </details>
  <details open>
    <summary>&nbsp;&nbsp;&nbsp;📕 04. 어플리케이션 설계</summary>
  <details open>
    <summary>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-cd64f9a7058246c2b7fff1f1451defc2?pvs=4">01. 인증 및 인가</summary>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/01-a3d187034b944e1aa44252a00f8befea?pvs=4">01-1. 기술 분석 및 시스템 사양 파악</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-1dbe517158de4555945b5dbd165450ba?pvs=4">01-2. 후보 방식 분류</a>      
  </details>  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/02-API-948250aef1df49e681ab4eb07b9d61d0?pvs=4">02. 화면별 API 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/03-API-da21c364096143228c581b6ef8db9add?pvs=4">03. API 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/04-JOB-e090360c4128408e9c1840aed55ff728?pvs=4">04. 배치 JOB 정의</a><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📜 <a href="https://www.notion.so/05-52849ca93a8d4ff3a5be954670852e2a?pvs=4">05. 개발 표준 정의</a><br>
  </details>

</details>

## 🔘 사용 기술

### 프론트엔드 개발
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/tailwindcss-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white"> <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/chart.js-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=black"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=black">

### 애플리케이션 개발
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/spring batch-6DB33F?style=for-the-badge&logo=..&logoColor=white"> <img src="https://img.shields.io/badge/quartz-007396?style=for-the-badge&logo=quartz&logoColor=white">

### 데이터베이스
<img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"> <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"> <img src="https://img.shields.io/badge/elasticache-C925D1?style=for-the-badge&logo=amazonelasticache&logoColor=white">

### 빌드 및 배포
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> <img src="https://img.shields.io/badge/apache http server-D22128?style=for-the-badge&logo=apache&logoColor=black">  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black"> <img src="https://img.shields.io/badge/amazon apigateway-FF4F8B?style=for-the-badge&logo=amazonapigateway&logoColor=black"> <img src="https://img.shields.io/badge/aws elb-8C4FFF?style=for-the-badge&logo=awselasticloadbalancing&logoColor=black"> <img src="https://img.shields.io/badge/amazon ecs-FF9900?style=for-the-badge&logo=amazonecs&logoColor=black"> <img src="https://img.shields.io/badge/aws fargate-FF9900?style=for-the-badge&logo=awsfargate&logoColor=black">

### 형상관리
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### 보안
<img src="https://img.shields.io/badge/aws secrets manager-DD344C?style=for-the-badge&logo=awssecretsmanager&logoColor=white">


## 🔘 인프라 아키텍처

### 1️⃣ 온프레미스 아키텍처
![제목 없는 다이어그램 drawio (1)](https://github.com/97Fekim/work-we-out/assets/81150979/aba71622-1f20-4cc8-8269-ebb58aba83d9)
<br><br><br>

### 2️⃣ 컨테이너 기반 아키텍처
![인프라 Release1 0 1 (1)](https://github.com/97Fekim/work-we-out/assets/81150979/ebd15672-9ca9-4f3f-a8cd-5dd868923527)
#### ※ CI / CI
![인프라 Release 1 0 2](https://github.com/97Fekim/work-we-out/assets/81150979/9dad5fdf-ce92-4d03-bcf1-7a6c7e92e139)
<br><br><br>

### 3️⃣ 클라우드 네이티브 기반 아키텍처
> 2024.07 개발 예정
<br><br><br>

### 4️⃣ 컨테이너 오케스트레이션 기반 아키텍처
> 2024.12 개발 예정
<br><br><br>



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
   o <strong>원인</strong> : 서브쿼리 혹은 Oracle의 그룹함수 등이 불가피하게 사용되었다. 서브쿼리는 메인 캘린더의 렌더링시 속도 저하를 방지하기 위한 One Query 서비스에 사용되었고, 날짜와 관련 다양한 정보를 테이블에서 조회하기 위해 Oracle의 그룹 함수 몇 개가 사용되었다. 프로젝트의 대부분 SQL은 JQPL로 작성되었지만, DBMS 전환시에는 상기의 Native Query을 일일이 목표 DBMS에 맞게 수정해야 한다. 따라서 중복 개발이 발생한다. <br><br>
   o <strong>해결안</strong> : 명확한 해결방법은 찾지 못하였지만, Native Query를 JPQL로 대체하는 방법을 고안해야 한다. 서브쿼리를 대체하며 성능저하를 막는 비즈니스 로직을 고안하거나, QueryDSL을 사용하는 등의 방법을 찾아볼 수 있다. <br><br>
</details>

<details>
  <summary>📒 Stateless 세션 정책에 의해, 모든 Ajax 요청의 인증 실패</summary>
  <br> 
   o <strong>현상</strong> : Ajax 요청에만 유효하지 않은 세션ID가 담겨있었다. 의아함을 느껴 Ajax 가 아닌 다른 요청의 세션ID 를 분석해 본 결과, 서버에서 인증 가능한 세션ID를 매번 새로 만들어주고 있었다. <br><br>
   o <strong>원인</strong> : 세션 정책을 Stateless 로 설정해 둔 것이 원인. 비동기 통신인 AJAX는 Stateless한 SessionID를 보장하지 못하기 때문이다.<br><br>
   o <strong>해결안</strong> : 세션 관리 방식을 Default 방식인 IF_REQUIRED로 수정. 따라서 하나의 세션ID가 유지되도록 함(Statefull). <br>
  원인분석을 하면서 의도치 않게 CORS정책에 관해서 많이 공부하게 되었다. 초기에는 "CORS정책에 의한 인증 거부"를 원인으로 가정했기 때문이다. 또한 문제 해결 과정에서 AuthenticationEntryPoint(인증 실패)와 AccessDeniedHandler(인가 실패) 개념을 숙지하였고, Session와 Cookie의 관리방식을 눈으로 보며 체감할 수 있었다.<br><br>
</details>

<details>
  <summary>📒 다른 서버와 요청간 CORS 정책 위반</summary>
  <br> 
   o <strong>현상</strong> : 온디맨드배치 실행 요청(API서버 -> 배치서버)시 도메인 불일치로 인한 CORS 정책 위반 <br><br>
   o <strong>원인</strong> : 프로그램의 설계상, 자원이 서비스되는 최초 도메인은 API서버(8080 port)의 도메인이지만 온디맨드 배치 프로그램의 실행은 배치 서버(8081 port)에서 처리되기 때문에 CORS정책의 위반을 피할 수 없었다.<br><br>
   o <strong>해결안</strong> : JSONP를 이용한 GET방식 요청을 통해, CORS를 우회한 요청을 배치서버로 보낼 수 있었다. 이를 활용하여 정기 배치 도중 실패한 대상을 재수행하는 서비스를 구현 가능했다.<br><br>
</details>

<details>
  <summary>📒 JpaPagingItemReader의 N+1 문제</summary>
  <br> 
   o <strong>현상</strong> : chunk size = 1  조건에서 문제가 발생. 예를 들어 6건의 데이터가 존재하는 경우에, [2,4,6] 번째 Item은 processor로 전달되지 않는 버그가 발생.<br><br>
   o <strong>원인</strong> : 로그 분석 결과, [1,3,5] 번째 Item을 조회할때 각각 2번의 select qeury가 실행되어 [2,4,6] 번째 Item이 through pass 처리됨.<br><br>
   o <strong>해결안</strong> : 임시적으로 chunk size = 1000 으로 설정하여 해결함. ItemReader의 queryString join문 부분에 fetch를 삽입하여 해결하는 방안을 테스트할 예정.<br><br>
</details>

<details>
  <summary>📒 다중서버 환경에서 인증/인가 메커니즘의 이해</summary>
  <br> 
   o <strong>현상</strong> : 2개의 서버(API서버, 배치서버) 와 단일 DB의 아키텍처로 설계하였다. <br><br>
   o <strong>원인</strong> : 인증/인가 정보를 두 서버가 공유하는 방법이 필요했다. 로그인은 API서버를 통해 처리되어야 하지만, 배치서버 또한 인증정보를 가지고 있어야 했다. 이유는 [API서버 -> 배치서버] 요청 중 ADMIN 권한을 요구하는 API가 존재하기 때문이다. <br><br>
   o <strong>해결안</strong> : 다양한 솔루션이 존재하지만, 그 중 Redis Cach DB 를 두어 세션 저장소로 활용하는 방법을 택했다. 빈번한 로그인 과정에서 Main DB에 I/O가 가해지지 않는 다는 점이 큰 장점이기 때문이다. 프로그램에 가장 적합한 인증/인가 환경을 설계하고 구축하는 과정에서 다양한 아키텍처의 메커니즘과 장단점을 공부할 수 있었다. <br><br>
</details>

<details>
  <summary>📒 파라미터 변조 취약점 및 방어의 이해</summary>
  <br> 
   o <strong>현상</strong> : 접근 권한이 없는 자원(다른 사람의 운동일지, 타 그룹의 그룹정보 등)의 접근이 허용됨<br><br>
   o <strong>원인</strong> : 대표적인 웹 해킹 보안 취약점인 "파라미터 변조" 취약점이 노출 되어있었다.<br><br>
   o <strong>해결안</strong> : 컨트롤러 계층에서 권한검사를 수행하는 방식으로 대응하였다. 로그인 Session정보와 파라미터를 비교하여 권한검사를 수행하는 방식을 사용했다. 스프링시큐리티의 기능을 활용해 간단하게 방어하는 방법을 찾아 보았지만 뾰족한 수가 없어 보였기 때문에, 권한검사 로직을 일일이 삽입해야 했다. 운동일지ID, 회원ID, 그룹ID 등을 파라미터로 받는 본 서비스에서 무심코 발생할 수 있다는 점을 상기하며, 다시 한번 시큐어 코딩의 중요성을 일깨웠다. <br><br>
</details>

<details>
  <summary>📒 애플리케이션내에서 ALB의 고정IP를 조회할 수 없는 문제</summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> :  <br><br>
</details>

<details>
  <summary>📒 Request 경로의 하드코딩으로 인하여, 동적인 DNS 관리가 어려운 문제 </summary>
  <br> 
   o <strong>현상</strong> : <br><br>
   o <strong>원인</strong> : <br><br>
   o <strong>해결안</strong> :  <br><br>
</details>
