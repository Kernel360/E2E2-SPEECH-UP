# Speech up

---
## 발음 향상을 원하는 당신을 위한 AI가 스피치 분석 서비스 😎

---
- 발음 연습과 비교를 위한 대본
- 녹음을 AI가 분석하고, 점수 책정
- 여러 사람들과 내 녹음에 대한 공유 및 피드백
- 상위 5% 이내 회원에게 1대1 멘토자격 부여
  - 멘토 
    - 1대1 10분 멘토링 가능 (회당 3000₩)
    - 공유된 녹음에 대한 피드백 가능

- rank
  - ~ 71%     <span style="color: yellow;">초급</span>
  - 70% ~ 41% <span style="color: skyblue;">중급</span>
  - 40% ~ 21% <span style="color: cyan;">고급</span>
  - 20% ~ 6%  <span style="color: green;">달인</span>
  - 5% ~      <span style="color: red;">마스터</span>
---
## 컨벤션
[네이버 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/)

---
>### Framework
> > <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=Spring-Boot&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white"><br>
>
>### Language
> > <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E"><br>
>
>### DB
> > <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"><br>
---

## 현재 추가 된 기능
- 소셜 로그인 기능
  - OAuth 적용
  - Google 로그인 구현
  - Kakao 로그인 구현
  - GitHub 로그인 구현
- 토큰을 사용한 인증
- 녹음 파일 업로드 (local → server)
  - wav > pcm > byte[]
- 재생 가능한 녹음 파일 (server → local)
  - byte[] > pcm > wav
- 분석을 위한 API 호출
  - AI API/DATA (ETRI) 사용
  - pcm 형식의 비압축파일 (wav) 파일 지원
  - AI가 인식한 발음과 내 대본을 비교 (1 ~ 5점)
- Kakao Map Api 연동
  - 사용자 주변의 스피치 학원 탐색 (5km 이내)
- 회원 정보 관리
- 녹음 페이지 수정 필요
- 관리자 기능<br>
---

초기 화면입니다. **게시판**이 들어갈 곳입니다.<br>
비회원이 접근할 수 있는 페이지입니다.<br>
게시글 작성은 로그인이 필요합니다.
![스크린샷 2024-08-14 09 59 02](https://github.com/user-attachments/assets/91e53434-cebc-4769-9f6f-6998f504e208)

**로그인 화면**입니다. 별도의 회원가입 없이 **소셜 로그인**(최초 회원등록) 후 바로 이용 가능합니다.
![스크린샷 2024-08-14 09 59 30](https://github.com/user-attachments/assets/df2e6056-704e-4e01-a7e7-74b1832d2a26)

로그인 시 좌측 상단의 메뉴에 마이페이지, 로그아웃, 스피치 분석 버튼이 생깁니다.<br>
이 메뉴는 로그인시 항상 활성화 되는 버튼입니다.
![스크린샷 2024-08-14 09 59 45](https://github.com/user-attachments/assets/21609e17-31e4-4dff-bdac-459f08a1edcd)

현재 사용자의 **대본 리스트**입니다.<br>
사용자의 대본들을 모두 확인할 수 있습니다.<br>
대본 작성하기 버튼을 누르면 작성 페이지로, 리스트 목록의 아이템을 선택하면 조회 및 녹음 페이지로 넘어갑니다.
![스크린샷 2024-08-14 09 59 58](https://github.com/user-attachments/assets/76af2fcd-fc0c-4c6f-8716-612de9b918dd)

**대본 작성** 페이지입니다. 
새로운 대본을 생성할 수 있습니다.
![스크린샷 2024-08-14 10 00 09](https://github.com/user-attachments/assets/800cfac5-ea9f-4f82-97e9-1a3371658693)

**녹음 분석** 페이지 입니다.
녹음하기 버튼을 눌러 녹음을 진행하고, 업로드 버튼을 클릭합니다. 클릭 시 생기는 아이템은 녹음 파일이며, 분석하기 버튼을 누르면 API를 호출하여 인식된 음성을 텍스트 파일로 보여주고 1~5점 사이로 점수가 매겨집니다.
![스크린샷 2024-08-14 10 00 18](https://github.com/user-attachments/assets/931ee61a-026a-4088-9a14-dce7ba8785db)

---

### ERD
### ![스크린샷 2024-07-31 10 38 09](https://github.com/user-attachments/assets/3bdeebce-412f-41c6-8883-96cd2fc4d7cf)
---

