# 🏡 simsasookbak!🏡
### 지나가는 나그네여! 쉬었다가시오! 심사숙박!

> '심사숙박'은 야놀자, 호텔스컴바인과 같은 서비스를 모델로 삼아, 여행자들이 숙박 시설에 대한 정보를 얻고 예약 및 리뷰를 남길 수 있는 플랫폼입니다. <br>
> 🌊 심사숙박만의 차별화된 기능인 Allen AI를 연동한 내부 커뮤니티를 통한 리뷰 요약, 타 사이트의 해당 숙박 시설에 대한 리뷰 요약을 확인하실 수 있습니다.

🔗 [서비스 바로가기](http://ec2-3-36-33-79.ap-northeast-2.compute.amazonaws.com:8080/)

<br><br>
## 팀원 소개 
![image](https://github.com/anonymousOrmi/simsasookbak/assets/148677085/9cef7ee8-bf17-4f2f-a834-f4411a7d5876)

<br><br>

## 개발 일정

![TimelineGanttOverviewSection](https://github.com/anonymousOrmi/simsasookbak/assets/47330173/9de4fc09-4186-4d5a-ad34-40bbdb1902ab)

<br><br>

## 🌱 개발 언어 및 활용 기술

### FrontEnd
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"/><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"><img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"><img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">
### BackEnd
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
### Infra & Cooporate
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white"><img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white"><img src="https://img.shields.io/badge/Discord-4169E1?style=for-the-badge&logo=Discord&logoColor=white"/><img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"/>
### DB
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
          
<br><br>

##  User Flow

① 플랫폼의 가장 첫 페이지인 메인페이지를 기준으로 Flow를 나누어 작성하였습니다.   <br>
② 노란색은 페이지를 보라색은 이동 버튼 및 트리거를 의미하며, 다이아몬드 모형은 논리 로직을 의미합니다. <br>
③ 빨간색 화살표는 비회원도 접근이 가능합니다. 파란색 화살표는 일반 유저의 로그인 상태일 경우, 초록색 화살표의 경우에는 숙박업자가 로그인한 상태에 접근 가능합니다. <br> 
④ 메인페이지에서 로그인페이지 이동, 검색을 통한 숙소목록페이지 이동, 마이페이지 및 숙소 찾기 페이지로 등으로의 이동이 가능며, 공통적으로 모든 페이지에서 로그아웃이 가능합니다.<br>

![image](https://github.com/anonymousOrmi/simsasookbak/assets/109260733/40d182bd-e826-4e33-b464-d056792eb734)

<br><br>

## 요구사항 및 기능 명세  

화면 단위로 기능을 분류하고, 해당 기능을 이용할 수 있는 권한을 기입하였습니다. 

| 화면        | 기능             | 내용                        | 권한               |
|------------|-----------------|---------------------------|-------------------|
| 메인       | 숙소 검색          | 공지사항 제목, 내용 조회     |            |
|            | 인기 숙소 Top6 조회 | 별점 기반 Top6 숙소 추천      |                   |
|            | 인기 지역 Top6 조회 | 최근 예약이 많았던 숙소 위치 기반의 6개 지역 제공 |                |
|            | 유튜브 영상 조회    | 핫한 여행지 기준 유튜브 영상 API 조회 |               |
| 마이 페이지 | 회원 정보 수정      | 회원가입시 제공한 데이터 수정    | 회원(이용자/관리자/사업자) |
|            | 회원 탈퇴           | 회원 탈퇴                     |                   |
|            | 예약 내역 조회      | 예약 내역 확인, 취소 및 수정, 스케줄링 통한 예약일 확인 및 변경 |  |
| 로그인/로그아웃 | 로그인           | 회원 이메일, 비밀번호 입력     |              |
|            | 로그아웃           | 로그아웃                       | 회원(이용자/관리자/사업자) |
| 회원가입     | 회원가입           | 이름, 이메일, 비밀번호, 회원 종류, 핸드폰 번호, 생년월일 입력 | 비회원 |
|            | 이메일 인증        | Gmail SMTP 사용한 메일 전송   |                   |
| 숙소 리스트  | 모든 숙소 조회      | 등록되어진 모든 숙소 조회      |             |
|            | 검색 기반 숙소 조회 | 검색 키워드 및 입/퇴실 날짜 기반 숙소 조회 |              |
| 숙소 상세    | 숙소 상세 내역 조회 | 숙소 사진, 숙소명, 위치, 가격 등의 숙박업소 상세 내역 조회 |  |
|            | 숙소 내역 수정      | 숙소 사진, 숙소명, 위치, 가격 등의 숙박업소 상세 내역 수정 | 사업자(본인이 등록한 숙소) |
|            | 숙소 삭제           | 본인이 등록한 숙소에 한해서, 숙소 정보가 이용자에게 노출되지 않도록 비활성화 | |
|            | 방 정보 조회       | 숙소에 있는 방에 대한 정보 조회 |  |
|            | 방 정보 수정       | 방의 정보 수정                | 사업자(본인이 등록한 숙소) |
|            | 방 비활성화       | 해당 방을 비활성화해 이용자에게 노출X |              |
|            | 예약               | 방 예약                       | 사업자(본인이 등록한 숙소) |
| 숙소 관리   | 숙박업소 조회       | 등록된 업소들 조회             | 회원(사업자)       |
|            | 숙박업소 등록       | 숙박업소 사진, 위치 등의 정보 등록 및 변경 |         |
|            | 숙박업소 수정       | 숙박업소 수정                 |         |
|            | 숙박업소 삭제       | 숙박업소 삭제                 |         |
| 예약 관리   | 예약 상세 조회 및 상태 변경 & Email 전송 | 사업자(본인이 등록한 숙소) |
| 관리자 페이지 | 회원 조회        | 모든 회원 조회                 | 관리자            |
|            | 회원 검색           | 회원 이름 검색을 통한 회원 정보 조회 |             |
|            | 회원 탈퇴           | 관리자 권한으로 회원 강제 탈퇴 |               |
|            | 회원 권한 변경      | 숙박업자, 사용자, 탈퇴자로 권한 변경 |              |


<br><br>


## 데이터베이스 모델링(ERD)  

① 크게 회원테이블, 숙소테이블, 리뷰테이블, 예약 내역테이블, 유튜브테이블로 나누었습니다.    
② 숙소 / 숙소 서비스, 객실 / 객실 서비스 테이블 간 중간 테이블을 두어 매핑하였습니다.   
③ 이미지가 필요한 테이블은 첨부파일 테이블을 별도로 두어 관리하였습니다.

![image](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/80b230b1-3c67-4827-abed-5a73e47fb8b4)

<br><br>
##  API 명세서

![image](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/25db7554-a215-4c8c-9f0c-68786f2bd0d2)

<br><br>

## :factory: Architecture
![image](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/5f6dd742-1c26-4b01-a453-bc20fb67eec0)

<br><br>

##  프로젝트 구조
### FE + BE

📂 gradle  
📂 src  
┣ 📂 main  
┃ ┗ 📂 java  
┃ ┃ ┗ 📂 com   
┃ ┃ ┃ ┗ 📂 simsasookbak    
┃ ┃ ┃ ┃ ┗ 📂 accommodation     
┃ ┃ ┃ ┃ ┃ ┗ 📂 controller   
┃ ┃ ┃ ┃ ┃ ┗ 📂 domain    
┃ ┃ ┃ ┃ ┃ ┗ 📂 dto   
┃ ┃ ┃ ┃ ┗ 📂 email   
┃ ┃ ┃ ┃ ┃ ┗ 📂 domain   
┃ ┃ ┃ ┃ ┃ ┗ 📂 dto   
┃ ┃ ┃ ┃ ┃ ┗ 📂 service   
┃ ┃ ┃ ┃ ┗ 📂 external   
┃ ┃ ┃ ┃ ┃ ┗ 📂 ai    
┃ ┃ ┃ ┃ ┃ ┗ 📂 youtube   
┃ ┃ ┃ ┃ ┗ 📂 global   
┃ ┃ ┃ ┃ ┃ ┗ 📂 aop   
┃ ┃ ┃ ┃ ┃ ┗ 📂 config   
┃ ┃ ┃ ┃ ┃ ┗ 📂 exception   
┃ ┃ ┃ ┃ ┃ ┗ 📂 page     
┃ ┃ ┃ ┃ ┃ ┗ 📂 util     
┃ ┃ ┃ ┃ ┃ ┃ 📜 BaseEntity.class     
┃ ┃ ┃ ┃ ┗ 📂 member     
┃ ┃ ┃ ┃ ┃ ┗ 📂 controller     
┃ ┃ ┃ ┃ ┃ ┗ 📂 domain   
┃ ┃ ┃ ┃ ┃ ┗ 📂 dto   
┃ ┃ ┃ ┃ ┃ ┗ 📂 repository    
┃ ┃ ┃ ┃ ┃ ┗ 📂 service   
┃ ┃ ┃ ┃ ┗ 📂 reservation  
┃ ┃ ┃ ┃ ┃ ┗ 📂 controller   
┃ ┃ ┃ ┃ ┃ ┗ 📂 domain   
┃ ┃ ┃ ┃ ┃ ┗ 📂 dto    
┃ ┃ ┃ ┃ ┃ ┗ 📂 repository   
┃ ┃ ┃ ┃ ┃ ┗ 📂 service    
┃ ┃ ┃ ┃ ┃ ┗ 📂 scheduling    
┃ ┃ ┃ ┃ ┗ 📂 review    
┃ ┃ ┃ ┃ ┃ ┗ 📂 controller   
┃ ┃ ┃ ┃ ┃ ┗ 📂 domain    
┃ ┃ ┃ ┃ ┃ ┗ 📂 dto   
┃ ┃ ┃ ┃ ┃ ┗ 📂 repository    
┃ ┃ ┃ ┃ ┃ ┗ 📂 service       
┃ ┃ ┃ ┃ ┗ 📂 room    
┃ ┃ ┃ ┃ ┃ ┗ 📂 controller   
┃ ┃ ┃ ┃ ┃ ┗ 📂 domain      
┃ ┃ ┃ ┃ ┃ ┗ 📂 dto   
┃ ┃ ┃ ┃ ┃ ┗ 📂 repository   
┃ ┃ ┃ ┃ ┃ ┗ 📂 service     
┃ ┗ 📂 resources    
┃ ┃ ┗ 📂 static     
┃ ┃ ┃ ┗ 📂 css    
┃ ┃ ┃ ┃ ┗ 📜 adminPage.css    
┃ ┃ ┃ ┃ ┗ 📜 bootstrap.min.css    
┃ ┃ ┃ ┃ ┗ 📜 index.css    
┃ ┃ ┃ ┃ ┗ 📜 register.css    
┃ ┃ ┃ ┃ ┗ 📜 reservationForm.css    
┃ ┃ ┃ ┃ ┗ 📜 style.css    
┃ ┃ ┃ ┃ ┗ 📜 table.css     
┃ ┃ ┃ ┃ ┗ 📜 templatemo-topic-listing.css    
┃ ┃ ┗ 📂 img   
┃ ┃ ┗ 📂 js    
┃ ┃ ┃ ┗ 📜 adminPage.js      
┃ ┃ ┃ ┗ 📜 index.css    
┃ ┃ ┃ ┗ 📜 register.css    
┃ ┃ ┃ ┗ 📜 mypage.js  
┃ ┃ ┃ ┗ 📜 popularRegion.js    
┃ ┃ ┃ ┗ 📜 main.js     
┃ ┃ ┃ ┗ 📜 review.js     
┃ ┃ ┃ ┗ 📜 reservationList.js    
┃ ┃ ┃ ┗ 📜 updateReservation.js     
┃ ┗ 📂 templates       
┃ ┃ ┗ 📂 layout    
┃ ┃ ┗ 📜 accommodation-register.html      
┃ ┃ ┗ 📜 accommodation-update..html         
┃ ┃ ┗ 📜 adminPage.html      
┃ ┃ ┗ 📜 details.html      
┃ ┃ ┗ 📜 index.html     
┃ ┃ ┗ 📜 list-page.html      
┃ ┃ ┗ 📜 login.html      
┃ ┃ ┗ 📜 my-accommodation-list.html      
┃ ┃ ┗ 📜 my-reservation-list.html     
┃ ┃ ┗ 📜 mypageInfo.html     
┃ ┃ ┗ 📜 reservation-management.html        
┃ ┃ ┗ 📜 review-register.html        
┃ ┃ ┗ 📜 room-update.html     
┃ ┃ ┗ 📜 updateReservationPage.html    
┗ 📂 test  
┣ 📜 gradlew    
┣ 📜 gradlew.bat    
┣ 📜 README.md    
┣ 📜 settings.gradle     

<br><br>

## UI
- 숙소 정보 수정
  ![숙소 정보 수정](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/fa69957a-9d57-49f8-9a8a-73e322d31ed2)  
- 내 숙소 관리
  ![내 숙소 과닐](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/34b4e460-8762-4371-bb2a-aca56d1caa2a)  
- 예약 페이지
  ![예약 페이지](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/ff1d239d-f12c-4a5e-a20b-dfa915009827)  
- 리스트 페이지
  ![리스트 페이지](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/40c733cc-4a19-4d8f-804b-1d09dca72d8f)  
- 마이페이지
  ![내정보 수정](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/61a75236-f712-4f63-903d-da2744b2b753)  
- 내 예약 내역
  ![내 예약내역](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/b3944edb-93f5-4b53-bde4-10932d435835)  
- 회원가입
  ![회원가입](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/25b49b0f-d765-45e9-b160-45457bbaa32b)  
- 로그인
  ![로그인](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/6e20618c-7edf-43b9-848e-7d6225ad1a02)  
- 관리자 페이지
  ![관리자 페이지](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/2b89a78f-96e7-4164-bb84-4d987b005816)  
- 숙소 상세  
  ![숙소상세페이지](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/877761e0-6afa-44a9-a734-8576387db7b4)  
- 메인 페이지  
  ![메인페이지](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/68c29836-ff61-46da-81e1-6995fd9dc4b1)  

<br><br>
## 개발 이슈
- 앨런 데이터 가공 

🔗 [이슈 목록 상세 확인](https://github.com/anonymousOrmi/simsasookbak/issues?q=is%3Aissue+is%3Aclosed)

<br><br>
## 개발 회고
### 🧑‍💻 이영석  
팀장으로 참여해서 CI/CD 나 PR 템플릿처럼 이전에 경험해 보지 못했던 부분을 적용해 볼 수 있어서 좋았습니다.<br> 
이젠 설계부터 배포까지 혼자서도 할 수 있겠다는 자신감도 생겼습니다! <br>
짧은 시간동안 빠르게 진행된 프로젝트로 어려움도 많았지만, 끝까지 포기하지 않고 열심히 참여해주신 팀원분들께 진심으로 감사의 말씀 드립니다!  

### 🧑‍💻 김상형  
여러가지 고려사항과 오류들을 만나면서 해결방법을 고민했습니다.<br>
혼자 였다면 해결하지 못한 부분들이 훨씬 많았을 것 같습니다. 모두가 자신의 일인 것 처럼 토론했던 것이 좋았습니다. <br>
또한, 모두가 참여하는 프로젝트여서 좋았습니다  

### 👩🏻‍💻 박지은  
방대한 Spring의 세계에서 맛보기를 해볼 수 있는 기회였습니다. 또한, 인프라 및 CI/CD 구축을 경험하면서 개발 환경을 구성해보는 경험을 하게되었습니다.  <br>
회사에서 열심히 일하고 있을 제 모습을 상상하니 벌써부터 설레입니다! 👩🏻‍💻 ❤️ ㅎㅎ 백엔드 개발자로서의 제 모습이 기대가 되네요! <br>
재밌는 오르미 분들을 만나서 짧은 4.5개월의 기간 동안 즐거운 생활을 할수 있었습니다! 모두 좋은 결과를 얻을 수 있었으면 좋겠습니다! 화이팅!  

### 🧑‍💻 송찬혁  
프로젝트를 또 경험하면서 저번에는 정리하지 못한 것들을 해가며 약간 더 성장 할 수 있었습니다. 또 프로젝트를 하고싶어질 정도로 재밌었습니다.  

### 🧑‍💻 정민석  
실력이 뛰어난 팀원들의 도움 덕분에 프로젝트에 조금이나마 기여할 수 있었다. 복잡한 쿼리도 능숙하게 작성할 수 있도록 공부해야겠다고 생각했다.  

### 👩🏻‍💻 최보현  
이전 프로젝트에 비해 DB 설계 및 매핑 과정이 복잡해지면서 다시 한번 설계의 중요성을 느꼈습니다. <br>
짧은 기간동안 많이 배워갑니다 ! 모두 고생하셨어요.  
