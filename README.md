# Speech up

---
## who??
**발음 향상을 목표로 삼고 싶은 모든 고객이 대상**

---
- 1분 스피치를 위한 대본
- 내 1분 스피치 녹음을 AI 가 점수 책정
- 여러 사람들과 내 녹음에 대한 공유 및 피드백

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
- 대본 (사용자가 원하는 대본 작성)
- 녹음 파일 (사용자의 local 에서 불러오는 방식)
- 분석을 위한 API 호출
  - Speech-flow API 호출
  - 녹음 파일을 기반으로 문자열 분석
  - 분석 내용 응답
- 소셜 로그인 기능
  - OAuth 적용
  - 구글 로그인 구현
- 세션을 이용한 페이지 구현
  - ex) 로그인 시 홈페이지 로그인 버튼이 로그아웃 버튼으로 변경
  - ex) 세션 없을 시 대본 작성 및 분석 기능 제공
- 녹음 분석 기능
- 게시판 기능

---

## 추가 해야 할 기능
- 회원 정보 관리
- 관리자 기능
- 세션 -> 토큰 (필요하다 생각하면?)
- 댓글 기능<br>
.<br>
.<br>
.
---

## Tree
<details> 

```
📦 
├─ .github
│  └─ ISSUE_TEMPLATE
│     ├─ 기능-구현.md
│     └─ 버그-리포트.md
├─ .gitignore
├─ .idea
│  └─ .gitignore
├─ Dockerfile
├─ HELP.md
├─ README.md
├─ build.gradle
├─ build
│  └─ resources
│     └─ main
│        └─ application.yaml
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ speech
   │  │        └─ up
   │  │           ├─ UpApplication.java
   │  │           ├─ api
   │  │           │  ├─ converter
   │  │           │  │  └─ WavToRaw.java
   │  │           │  ├─ etri
   │  │           │  │  ├─ controller
   │  │           │  │  │  └─ ETRIApiController.java
   │  │           │  │  ├─ dto
   │  │           │  │  │  ├─ AiRequest.java
   │  │           │  │  │  ├─ RequestVoiceToTextApiDto.java
   │  │           │  │  │  └─ ResponseVoiceToTextApiDto.java
   │  │           │  │  ├─ service
   │  │           │  │  │  └─ VoiceToTextService.java
   │  │           │  │  ├─ type
   │  │           │  │  │  └─ ApiType.java
   │  │           │  │  └─ url
   │  │           │  │     ├─ ApiUrl.java
   │  │           │  │     ├─ PronunciationAI.java
   │  │           │  │     ├─ RecognizedAI.java
   │  │           │  │     └─ UrlCollector.java
   │  │           │  └─ speechFlow
   │  │           │     ├─ controller
   │  │           │     │  └─ SpeechFlowAPIController.java
   │  │           │     └─ service
   │  │           │        └─ VoiceToTextSpeechFlowService.java
   │  │           ├─ board
   │  │           │  ├─ controller
   │  │           │  │  └─ BoardController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseBoardEntity.java
   │  │           │  │  └─ BoardEntity.java
   │  │           │  ├─ repository
   │  │           │  │  └─ BoardRepository.java
   │  │           │  └─ service
   │  │           │     ├─ BoardService.java
   │  │           │     ├─ checkValue
   │  │           │     │  ├─ CheckListForPagination.java
   │  │           │     │  └─ CheckParamForPagination.java
   │  │           │     └─ dto
   │  │           │        ├─ BoardAddDto.java
   │  │           │        ├─ BoardGetDto.java
   │  │           │        ├─ BoardIsUseDto.java
   │  │           │        └─ BoardUpdateDto.java
   │  │           ├─ common
   │  │           │  ├─ dto
   │  │           │  │  └─ ApiExceptionResponse.java
   │  │           │  ├─ enums
   │  │           │  │  └─ StatusCode.java
   │  │           │  └─ exception
   │  │           │     ├─ custom
   │  │           │     │  ├─ CustomIOException.java
   │  │           │     │  ├─ CustomIllegalArgumentException.java
   │  │           │     │  └─ CustomRuntimeException.java
   │  │           │     ├─ handler
   │  │           │     │  └─ ExceptionController.java
   │  │           │     └─ http
   │  │           │        ├─ BadRequestException.java
   │  │           │        ├─ ForbiddenException.java
   │  │           │        ├─ HttpBaseException.java
   │  │           │        ├─ InternalServerErrorException.java
   │  │           │        ├─ NotFoundException.java
   │  │           │        └─ UnAuthorizedException.java
   │  │           ├─ demo
   │  │           │  ├─ BoardPageController.java
   │  │           │  ├─ HomePageController.java
   │  │           │  ├─ RegisterPageController.java
   │  │           │  └─ ScriptPageController.java
   │  │           ├─ oAuth
   │  │           │  ├─ common
   │  │           │  │  ├─ ResponseCode.java
   │  │           │  │  └─ ResponseMessage.java
   │  │           │  ├─ config
   │  │           │  │  └─ WebSecurityConfig.java
   │  │           │  ├─ entity
   │  │           │  │  └─ CustomOAuth2User.java
   │  │           │  ├─ filter
   │  │           │  │  └─ JwtAuthenticationFilter.java
   │  │           │  ├─ handler
   │  │           │  │  └─ OAuth2SuccessHandler.java
   │  │           │  ├─ provider
   │  │           │  │  ├─ GithubProvider.java
   │  │           │  │  ├─ GoogleProvider.java
   │  │           │  │  ├─ JwtProvider.java
   │  │           │  │  ├─ KakaoProvider.java
   │  │           │  │  ├─ Provider.java
   │  │           │  │  └─ ProviderOAuth.java
   │  │           │  └─ service
   │  │           │     ├─ implement
   │  │           │     │  ├─ OAuth2UserServiceImplement.java
   │  │           │     │  └─ UserAuthorizationType.java
   │  │           │     └─ servicetype
   │  │           │        ├─ LevelType.java
   │  │           │        └─ ProviderType.java
   │  │           ├─ report
   │  │           │  ├─ controller
   │  │           │  │  └─ ReportController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseReportEntity.java
   │  │           │  │  ├─ ReportEntity.java
   │  │           │  │  └─ type
   │  │           │  │     └─ ReportContentAndScore.java
   │  │           │  ├─ repository
   │  │           │  │  └─ ReportRepository.java
   │  │           │  └─ service
   │  │           │     ├─ ReportService.java
   │  │           │     └─ dto
   │  │           │        └─ ReportAddDto.java
   │  │           ├─ script
   │  │           │  ├─ controller
   │  │           │  │  ├─ RecordController.java
   │  │           │  │  └─ ScriptController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseRecordEntity.java
   │  │           │  │  ├─ BaseScriptEntity.java
   │  │           │  │  ├─ RecordEntity.java
   │  │           │  │  └─ ScriptEntity.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ RecordRepository.java
   │  │           │  │  └─ ScriptRepository.java
   │  │           │  └─ service
   │  │           │     ├─ RecordService.java
   │  │           │     ├─ ScriptService.java
   │  │           │     ├─ dto
   │  │           │     │  ├─ RecordAddDto.java
   │  │           │     │  ├─ RecordGetDto.java
   │  │           │     │  ├─ RecordIsUseDto.java
   │  │           │     │  ├─ ScriptAddDto.java
   │  │           │     │  ├─ ScriptGetDto.java
   │  │           │     │  ├─ ScriptIsUseDto.java
   │  │           │     │  └─ ScriptUpdateDto.java
   │  │           │     └─ recordFile
   │  │           │        └─ RecordFile.java
   │  │           └─ user
   │  │              ├─ controller
   │  │              │  └─ UserController.java
   │  │              ├─ entity
   │  │              │  └─ UserEntity.java
   │  │              ├─ repository
   │  │              │  └─ UserRepository.java
   │  │              └─ service
   │  │                 ├─ UserService.java
   │  │                 └─ dto
   │  │                    └─ UserGetInfoDto.java
   │  └─ resources
   │     ├─ application-test.yaml
   │     ├─ application.yaml
   │     ├─ static
   │     │  ├─ css
   │     │  │  ├─ board-write.css
   │     │  │  ├─ header-style.css
   │     │  │  ├─ home-style.css
   │     │  │  ├─ record-style.css
   │     │  │  ├─ script-style.css
   │     │  │  ├─ script-write.css
   │     │  │  └─ signIn-style.css
   │     │  ├─ images
   │     │  │  ├─ github-logo.png
   │     │  │  ├─ google-logo.png
   │     │  │  └─ kakao-logo.png
   │     │  └─ scriptPage
   │     │     └─ js
   │     │        ├─ addRecordingToList.js
   │     │        ├─ addTokenSession.js
   │     │        ├─ analyticRecord.js
   │     │        ├─ boardModify.js
   │     │        ├─ checkBoardOwner.js
   │     │        ├─ loadLocalRecord.js
   │     │        ├─ localStoragePath.js
   │     │        ├─ record.js
   │     │        ├─ saveRecord.js
   │     │        ├─ scriptDetail.js
   │     │        ├─ scriptList.js
   │     │        ├─ scriptWrite.js
   │     │        └─ userMe.js
   │     └─ templates
   │        ├─ board-detail.html
   │        ├─ board-write.html
   │        ├─ board.html
   │        ├─ home.html
   │        ├─ script-list.html
   │        ├─ script-write.html
   │        ├─ script.html
   │        └─ signIn.html
   └─ test
      └─ java
         └─ com
            └─ speech
               └─ up
                  ├─ UpApplicationTests.java
                  ├─ api
                  │  └─ speechFlow
                  │     └─ VoiceToTextTest.java
                  ├─ script
                  │  ├─ controller
                  │  │  └─ ScriptControllerTest.java
                  │  ├─ entity
                  │  │  └─ ScriptEntityTest.java
                  │  ├─ repository
                  │  │  └─ ScriptRepositoryTest.java
                  │  └─ service
                  │     └─ ScriptServiceTest.java
                  └─ user
                     ├─ controller
                     │  └─ UserControllerTest.java
                     ├─ entity
                     │  └─ UserEntityTest.java
                     └─ service
                        └─ UserServiceTest.java
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)
</details>

---

초기 미로그인시 화면 구성입니다. **게시판**이 들어갈 곳입니다.
![스크린샷 2024-08-14 09 59 02](https://github.com/user-attachments/assets/91e53434-cebc-4769-9f6f-6998f504e208)

로그인 화면입니다. 별도의 회원가입 없이 **소셜 로그인**(최초 회원가입) 후 바로 이용 가능합니다.
![스크린샷 2024-08-14 09 59 30](https://github.com/user-attachments/assets/df2e6056-704e-4e01-a7e7-74b1832d2a26)

로그인 시 좌측 상단의 메뉴에 마이페이지, 로그아웃, 스피치 분석 버튼이 생깁니다. 이 메뉴는 로그인 활성화 시 계속 떠있는 버튼입니다.
![스크린샷 2024-08-14 09 59 45](https://github.com/user-attachments/assets/21609e17-31e4-4dff-bdac-459f08a1edcd)

현재 사용자의 **대본 리스트**입니다. 사용자의 대본들을 모두 확인할 수 있고, 대본 작성하기 버튼을 누르면 작성 페이지로, 리스트 목록의 아이템을 선택하면 조회 및 녹음 페이지로 넘어갑니다.
![스크린샷 2024-08-14 09 59 58](https://github.com/user-attachments/assets/76af2fcd-fc0c-4c6f-8716-612de9b918dd)

작성 페이지로, **새로운 대본을 생성**할 수 있습니다.
![스크린샷 2024-08-14 10 00 09](https://github.com/user-attachments/assets/800cfac5-ea9f-4f82-97e9-1a3371658693)

**대본을 확인하고 녹음**할 수 있는 페이지 입니다. 녹음하기 버튼을 눌러 녹음을 진행하고, 업로드 버튼을 클릭합니다. 클릭 시 생기는 아이템은 녹음 파일이며, 분석하기 버튼을 누르면 API를 호출하여 인식된 음성을 텍스트 파일로 보여주고 1~5점 사이로 점수가 매겨집니다.
![스크린샷 2024-08-14 10 00 18](https://github.com/user-attachments/assets/931ee61a-026a-4088-9a14-dce7ba8785db)

---

## 컨벤션
[코딩 컨벤션](https://naver.github.io/hackday-conventions-java/)
---

### 화면설계
![스크린샷 2024-07-31 10 36 25](https://github.com/user-attachments/assets/6c47baf1-2169-45a5-8bff-c2f2ae587e83)
---

### ERD
### ![스크린샷 2024-07-31 10 38 09](https://github.com/user-attachments/assets/3bdeebce-412f-41c6-8883-96cd2fc4d7cf)
---

### 기능 명세서
https://sincere-nova-ec6.notion.site/495b3e7a2488442fa52a310af4f77766?pvs=4
---

### API 명세서
https://sincere-nova-ec6.notion.site/API-b216485c10e04126a28b0ce86bae203a?pvs=4
---

### 요구 사항 명세서
https://sincere-nova-ec6.notion.site/9058005fe69c4dbb926c547cf7a1f145?pvs=4
