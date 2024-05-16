# 🏡 simsasookbak!🏡
### 지나가는 나그네여! 쉬었다가시오! 심사숙박!

> '심사숙박'은 야놀자, 호텔스컴바인과 같은 서비스를 모델로 삼아, 여행자들이 숙박 시설에 대한 정보를 얻고 예약 및 리뷰를 남길 수 있는 플랫폼입니다. <br>
> 🌊 심사숙박만의 차별화된 기능인 Allen AI를 연동한 내부 커뮤니티를 통한 리뷰 요약, 타 사이트의 해당 숙박 시설에 대한 리뷰 요약을 확인하실 수 있습니다.

## 팀원 소개 
이영석 (PM)  
김상형  
박지은  
송찬혁  
정민석  
최보현  

## 개발 일정

![TimelineGanttOverviewSection](https://github.com/anonymousOrmi/simsasookbak/assets/47330173/9de4fc09-4186-4d5a-ad34-40bbdb1902ab)




## 🌱 개발 언어 및 활용 기술

FE

- HTML
- CSS
- JS
- BootStrap
- Thymeleaf

BE 

- SpringBoot
- JPA

DB

- MySQL

환경

- AWS
- Intellij
- Github
- Discord + 웹훅을 통한 깃헙 알림 설정

##  User Flow

① 플랫폼의 가장 첫 페이지인 메인페이지를 기준으로 Flow를 나누어 작성하였습니다.   <br>
② 노란색은 페이지를 보라색은 이동 버튼 및 트리거를 의미하며, 다이아몬드 모형은 논리 로직을 의미합니다. <br>
③ 빨간색 화살표는 비회원도 접근이 가능합니다. 파란색 화살표는 일반 유저의 로그인 상태일 경우, 초록색 화살표의 경우에는 숙박업자가 로그인한 상태에 접근 가능합니다. <br> 
④ 메인페이지에서 로그인페이지 이동, 검색을 통한 숙소목록페이지 이동, 마이페이지 및 숙소 찾기 페이지로 등으로의 이동이 가능며, 공통적으로 모든 페이지에서 로그아웃이 가능합니다.<br>

![image](https://github.com/anonymousOrmi/simsasookbak/assets/109260733/40d182bd-e826-4e33-b464-d056792eb734)


## 요구사항 및 기능 명세  

화면 페이지별로 기능 명세하였으며, 각 권한에 따라 나누었습니다.

![image](https://github.com/anonymousOrmi/simsasookbak/assets/109260733/9367a7b4-e45b-4637-a013-d231b845b8c7)
![image](https://github.com/anonymousOrmi/simsasookbak/assets/109260733/5cd1832c-e0ba-4f0c-88e0-b847d50ede66)   





## 데이터베이스 모델링(ERD)  

① 크게 회원테이블, 숙소테이블, 리뷰테이블, 예약 내역테이블, 유튜브테이블로 나누었습니다.    
② 숙소 / 숙소 서비스, 객실 / 객실 서비스 테이블 간 중간 테이블을 두어 매핑하였습니다.   
③ 이미지가 필요한 테이블은 첨부파일 테이블을 별도로 두어 관리하였습니다.

![image](https://github.com/anonymousOrmi/simsasookbak/assets/109260733/4e9235b1-32ed-42df-8864-e6251c1557d8)

##  API 명세서

![image](https://github.com/anonymousOrmi/simsasookbak/assets/42295097/b5df2ef5-73b5-4ea7-82eb-5536a3ed287a)


## :factory: Architecture
![image](https://github.com/anonymousOrmi/simsasookbak/assets/47330173/846397ab-8595-49e4-b3fd-58e04116b200)


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



## 개발 이슈
[이슈 이름](링크 주소)

<br></br>
## 개발 회고
🧑‍💻 이영석
    
🧑‍💻 김상형
    
🧑‍💻 박지은
    
🧑‍💻 송찬혁
    
🧑‍💻 정민석
    
🧑‍💻 최보현
    
