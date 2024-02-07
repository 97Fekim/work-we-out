# work-we-out

## 소개

## 프로젝트 기간

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

## 설계

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

## 사용 기술

## 데이터모델

## 인프라 아키텍처

## 트러블슈팅 및 배운 점
