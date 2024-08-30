# SpeechUp

## 소개

[SpeechUp](https://speechup.duckdns.org/)는 발음 연습과 음성 인식 기술을 활용하여 사용자의 발음 능력을 향상시키기 위한 서비스입니다.<br> 이 사이트는 발음 테스트 도구를 제공하여 사용자들이 보다 정확하고 자연스러운 발음을 구사할 수 있도록 돕습니다.
---

## 주요 기능

- ETRI 의 음성 인식 API을 활용하여 발음 연습을 지원합니다.
- 사용자의 녹음 파일(.wav)을 기반으로 발음을 분석하고 점수를 책정하는 기능을 제공합니다.
- 발음 연습과 관련된 질문과 정보를 공유할 수 있는 포럼을 제공합니다.
- 사용자의 위치를 받아와 주변의 스피치 학원 정보를 재공합니다.
---

## 사용 방법

1. **회원 가입**: 일반적인 회원가입 필요 없이 소셜로 로그인합니다.
2. **발음 분석**: 사용자가 발음할 대본을 작성하고, 녹음한 파일을 업로드하여 분석을 진행합니다.
3. **발음 점수 책정**: 분석이 완료되면 활성화된 분석결과 버튼을 클릭하고 분석 결과를 확인합니다.
4. **포럼 이용**: 커뮤니티 포럼에서 질문을 하고 정보를 공유합니다.
---

## 예제
<details>
다음은 발음 연습을 위한 예제 문장입니다:

- "간장 공장 공장장은 강 공장장이고 된장 공장 공장장은 공 공장장이다."
- "내가 그린 기린 그림은 긴 기린 그림이고 니가 그린 기린 그림은 안긴 기린 그림이다."
- "상표 붙인 큰 깡통은 깐 깡통인가 안 깐 깡통인가."
</details>

---
**홈** 화면 입니다.
대표적인 기능 및 미로그인 상태의 사용자가 사용할 수 있는 메뉴가 있고, 사용자의 후기를 보여줍니다.
<img width="1673" alt="스크린샷 2024-08-29 12 47 08" src="https://github.com/user-attachments/assets/32e48752-7300-42e7-b9f0-ff18cc4607ea">

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

## API
 - ETRI
   - 음성 인식 및 분석
 - Kakao
   - 소셜 로그인, 카카오맵
 - Google
   - 소셜 로그인
 - GitHub
   - 소셜 로그인

## 컨벤션
- 코딩 컨벤션
  - [네이버 코딩 컨벤션](https://naver.github.io/hackday-conventions-java/)
- git 컨벤션
  - git flow 브랜치 전략 사용
  - mile stone 으로 해야할 일과 진척도 확인
  - issue 템플릿 이용

---
> ### Framework
> > <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> 
>
> ### Language
> > <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/Thymeleaf-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
>
> ### Test
> > <img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white"> 
>
> ### Docs
> > <img src="https://img.shields.io/badge/Javadoc-007396?style=for-the-badge&logo=java&logoColor=white"> 
>
> ### DB
> > <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
>
> ### Auth
> > <img src="https://img.shields.io/badge/OAuth%202.0-3D9B3F?style=for-the-badge&logo=oauth&logoColor=white"> <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white"> <img src="https://img.shields.io/badge/Kakao-FFCD00?style=for-the-badge&logo=kakao&logoColor=white"> <img src="https://img.shields.io/badge/Google-4285F4?style=for-the-badge&logo=google&logoColor=white"> <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/SSL-4D4D4D?style=for-the-badge&logo=ssl&logoColor=white">
>
> ### Infra
> > <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/GCP-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white"> <img src="https://img.shields.io/badge/.env-4C4C4C?style=for-the-badge&logo=dotenv&logoColor=white">
>
> ### CI/CD
> > <img src="https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white"> 
>
> ### Version Control
> > <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white">

---

## 현재 추가 된 기능
- 소셜 로그인 기능
  - OAuth 적용
  - Google 로그인 구현
  - Kakao 로그인 구현
  - GitHub 로그인 구현
- 토큰을 사용한 인증
- 게시글 기능
- 댓글 기능
- 대본 기능
- 녹음 분석 기능
- 회원 정보 열람 및 탈퇴 기능
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
- 관리자 페이지
  - 회원 계정 정지기능
---

## 차후 추가 기능
- 인식 된 음성 텍스트를 통해 AI 가 피드백 해주는 기능
- 웹 페이지 내 직접 녹음 후 분석 기능 (현재 지적재산권 문제로 mp3 파일 지원 불가)
- 스피치 학원 간편 등록 기능
- 발음 챌린지 기능
- 회원 등급 별 멘토 멘티 기능
---

## Tree
<details> 

```
📦 
├─ .github
│  ├─ ISSUE_TEMPLATE
│  │  ├─ code-refactor.md
│  │  ├─ 기능-구현.md
│  │  └─ 버그-리포트.md
│  └─ workflows
│     ├─ gradle-publish.yml
│     └─ gradle.yml
├─ .gitignore
├─ .idea
│  └─ .gitignore
├─ Dockerfile
├─ HELP.md
├─ README.md
├─ build.gradle
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
   │  │           ├─ admin
   │  │           │  ├─ controller
   │  │           │  │  ├─ AdminController.java
   │  │           │  │  ├─ AdminPageController.java
   │  │           │  │  └─ OpenDataController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ StatisticsAdd.java
   │  │           │  │  └─ StatisticsGet.java
   │  │           │  ├─ entity
   │  │           │  │  └─ StatisticsEntity.java
   │  │           │  ├─ repository
   │  │           │  │  └─ StatisticsRepository.java
   │  │           │  └─ service
   │  │           │     ├─ StatisticsScheduling.java
   │  │           │     └─ StatisticsService.java
   │  │           ├─ api
   │  │           │  ├─ converter
   │  │           │  │  └─ WavToRaw.java
   │  │           │  └─ etri
   │  │           │     ├─ controller
   │  │           │     │  └─ ETRIApiController.java
   │  │           │     ├─ dto
   │  │           │     │  ├─ AiRequest.java
   │  │           │     │  ├─ RequestPronunciationDto.java
   │  │           │     │  ├─ RequestRecognitionDto.java
   │  │           │     │  ├─ ResponsePronunciationApiDto.java
   │  │           │     │  └─ ResponseRecognitionDto.java
   │  │           │     ├─ service
   │  │           │     │  └─ VoiceToTextService.java
   │  │           │     ├─ type
   │  │           │     │  └─ ApiType.java
   │  │           │     └─ url
   │  │           │        ├─ ApiUrl.java
   │  │           │        ├─ PronunciationAI.java
   │  │           │        ├─ RecognizedAI.java
   │  │           │        └─ UrlCollector.java
   │  │           ├─ auth
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
   │  │           │  ├─ aspect
   │  │           │  │  └─ LoggingAspect.java
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
   │  │           │  ├─ ReportPageController.java
   │  │           │  └─ ScriptPageController.java
   │  │           ├─ record
   │  │           │  ├─ controller
   │  │           │  │  └─ RecordController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseRecordEntity.java
   │  │           │  │  └─ RecordEntity.java
   │  │           │  ├─ repository
   │  │           │  │  └─ RecordRepository.java
   │  │           │  └─ service
   │  │           │     ├─ RecordService.java
   │  │           │     └─ dto
   │  │           │        ├─ RecordAddDto.java
   │  │           │        ├─ RecordGetDto.java
   │  │           │        └─ RecordIsUseDto.java
   │  │           ├─ reply
   │  │           │  ├─ controller
   │  │           │  │  └─ ReplyController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ ReplyBaseEntity.java
   │  │           │  │  └─ ReplyEntity.java
   │  │           │  ├─ repository
   │  │           │  │  └─ ReplyRepository.java
   │  │           │  └─ service
   │  │           │     ├─ ReplyService.java
   │  │           │     └─ dto
   │  │           │        ├─ ReplyAddDto.java
   │  │           │        ├─ ReplyGetDto.java
   │  │           │        ├─ ReplyIsUseDto.java
   │  │           │        └─ ReplyUpdateDto.java
   │  │           ├─ report
   │  │           │  ├─ entity
   │  │           │  │  ├─ ReportEntity.java
   │  │           │  │  └─ dto
   │  │           │  │     └─ ReportGetDto.java
   │  │           │  ├─ repository
   │  │           │  │  └─ ReportRepository.java
   │  │           │  └─ service
   │  │           │     └─ ReportService.java
   │  │           ├─ script
   │  │           │  ├─ controller
   │  │           │  │  └─ ScriptController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseScriptEntity.java
   │  │           │  │  └─ ScriptEntity.java
   │  │           │  ├─ repository
   │  │           │  │  └─ ScriptRepository.java
   │  │           │  └─ service
   │  │           │     ├─ ScriptService.java
   │  │           │     └─ dto
   │  │           │        ├─ ScriptAddDto.java
   │  │           │        ├─ ScriptGetDto.java
   │  │           │        ├─ ScriptIsUseDto.java
   │  │           │        └─ ScriptUpdateDto.java
   │  │           └─ user
   │  │              ├─ controller
   │  │              │  └─ UserController.java
   │  │              ├─ entity
   │  │              │  └─ UserEntity.java
   │  │              ├─ repository
   │  │              │  └─ UserRepository.java
   │  │              └─ service
   │  │                 ├─ UserScheduling.java
   │  │                 ├─ UserService.java
   │  │                 └─ dto
   │  │                    └─ UserGetInfoDto.java
   │  └─ resources
   │     ├─ application-test.yaml
   │     ├─ application.yaml
   │     ├─ logback-spring.xml
   │     ├─ static
   │     │  ├─ .well-known
   │     │  │  └─ pki-validation
   │     │  │     └─ 8EF57DB308F91206366EB1DC0CF95E15.txt
   │     │  ├─ css
   │     │  │  ├─ admin-view.css
   │     │  │  ├─ board-style.css
   │     │  │  ├─ board-write.css
   │     │  │  ├─ header-style.css
   │     │  │  ├─ home-style.css
   │     │  │  ├─ kakaomap-style.css
   │     │  │  ├─ land-style.css
   │     │  │  ├─ my-page.css
   │     │  │  ├─ record-style.css
   │     │  │  ├─ report-style.css
   │     │  │  ├─ script-style.css
   │     │  │  ├─ script-write.css
   │     │  │  └─ signIn-style.css
   │     │  ├─ images
   │     │  │  ├─ github-logo.png
   │     │  │  ├─ google-logo.png
   │     │  │  ├─ kakao-logo.png
   │     │  │  ├─ tooltip-image1.png
   │     │  │  └─ tooltip-image2.png
   │     │  └─ js
   │     │     ├─ addRecordingToList.js
   │     │     ├─ addTokenSession.js
   │     │     ├─ adminUserHandler.js
   │     │     ├─ analyticRecord.js
   │     │     ├─ boardCount.js
   │     │     ├─ boardModify.js
   │     │     ├─ boardWrite.js
   │     │     ├─ checkAdministration.js
   │     │     ├─ checkBoardOwner.js
   │     │     ├─ checkLogined.js
   │     │     ├─ home.js
   │     │     ├─ loadLocalRecord.js
   │     │     ├─ localStoragePath.js
   │     │     ├─ map.js
   │     │     ├─ myPage.js
   │     │     ├─ record.js
   │     │     ├─ reply.js
   │     │     ├─ replyCount.js
   │     │     ├─ saveRecord.js
   │     │     ├─ scriptCount.js
   │     │     ├─ scriptDetail.js
   │     │     ├─ scriptList.js
   │     │     ├─ scriptWrite.js
   │     │     ├─ userDelete.js
   │     │     └─ userMe.js
   │     └─ templates
   │        ├─ admin-page.html
   │        ├─ board-detail.html
   │        ├─ board-edit.html
   │        ├─ board-write.html
   │        ├─ board.html
   │        ├─ home.html
   │        ├─ map.html
   │        ├─ myPage.html
   │        ├─ report.html
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
                  ├─ admin
                  │  ├─ controller
                  │  │  ├─ AdminControllerTest.java
                  │  │  ├─ AdminPageControllerTest.java
                  │  │  └─ OpenDataControllerTest.java
                  │  └─ service
                  │     ├─ StatisticsSchedulingTest.java
                  │     └─ StatisticsServiceTest.java
                  ├─ api
                  │  └─ etri
                  │     ├─ controller
                  │     │  └─ ETRIApiControllerTest.java
                  │     ├─ dto
                  │     │  ├─ RequestPronunciationDtoTest.java
                  │     │  ├─ RequestRecognitionDtoTest.java
                  │     │  ├─ ResponsePronunciationApiDtoTest.java
                  │     │  └─ ResponseRecognitionDto.java
                  │     ├─ type
                  │     │  └─ ApiTypeTest.java
                  │     └─ url
                  │        ├─ PronunciationAITest.java
                  │        ├─ RecognizedAITest.java
                  │        └─ UrlCollectorTest.java
                  ├─ auth
                  │  ├─ entity
                  │  │  └─ CustomOAuth2UserTest.java
                  │  ├─ filter
                  │  │  └─ JwtAuthenticationFilterTest.java
                  │  ├─ handler
                  │  │  └─ OAuth2SuccessHandlerTest.java
                  │  └─ provider
                  │     ├─ GithubProviderTest.java
                  │     ├─ GoogleProviderTest.java
                  │     ├─ JwtProviderTest.java
                  │     ├─ KakaoProviderTest.java
                  │     └─ ProviderTest.java
                  ├─ board
                  │  ├─ controller
                  │  │  └─ BoardControllerTest.java
                  │  ├─ repository
                  │  │  └─ BoardRepositoryTest.java
                  │  └─ service
                  │     └─ BoardServiceTest.java
                  ├─ common
                  │  ├─ enums
                  │  │  └─ StatusCodeTest.java
                  │  └─ exception
                  │     ├─ custom
                  │     │  ├─ CustomIOExceptionTest.java
                  │     │  ├─ CustomIllegalArgumentExceptionTest.java
                  │     │  └─ CustomRuntimeExceptionTest.java
                  │     └─ http
                  │        ├─ ForbiddenExceptionTest.java
                  │        ├─ InternalServerErrorExceptionTest.java
                  │        ├─ NotFoundExceptionTest.java
                  │        └─ UnAuthorizedExceptionTest.java
                  ├─ demo
                  │  ├─ BoardPageControllerTest.java
                  │  ├─ HomePageControllerTest.java
                  │  ├─ RegisterPageControllerTest.java
                  │  ├─ ReportPageControllerTest.java
                  │  └─ ScriptPageControllerTest.java
                  ├─ record
                  │  ├─ controller
                  │  │  └─ RecordControllerTest.java
                  │  ├─ repository
                  │  │  └─ RecordRepositoryTest.java
                  │  └─ service
                  │     ├─ RecordServiceTest.java
                  │     └─ dto
                  │        ├─ RecordAddDtoTest.java
                  │        └─ RecordIsUseDtoTest.java
                  ├─ reply
                  │  ├─ controller
                  │  │  └─ ReplyControllerTest.java
                  │  ├─ entity
                  │  │  └─ ReplyBaseEntityTest.java
                  │  ├─ repository
                  │  │  └─ ReplyRepositoryTest.java
                  │  └─ service
                  │     └─ ReplyServiceTest.java
                  ├─ report
                  │  ├─ entity
                  │  │  └─ dto
                  │  │     └─ ReportGetDtoTest.java
                  │  ├─ repository
                  │  │  └─ ReportRepositoryTest.java
                  │  └─ service
                  │     └─ ReportServiceTest.java
                  ├─ script
                  │  ├─ controller
                  │  │  └─ ScriptControllerTest.java
                  │  ├─ repository
                  │  │  └─ ScriptRepositoryTest.java
                  │  └─ service
                  │     └─ ScriptServiceTest.java
                  └─ user
                     ├─ controller
                     │  └─ UserControllerTest.java
                     ├─ repositiory
                     │  └─ UserRepositoryTest.java
                     └─ service
                        ├─ UserSchedulingTest.java
                        └─ UserServiceTest.java
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)</details>

### DB Diagram
<img width="722" alt="스크린샷 2024-08-29 12 27 03" src="https://github.com/user-attachments/assets/1293a3fd-12c3-4b71-a97c-f68c78a10f4e">

---

## 기획 (초기 구성)
<details>
### 화면설계
![스크린샷 2024-07-31 10 36 25](https://github.com/user-attachments/assets/6c47baf1-2169-45a5-8bff-c2f2ae587e83)
---

### 기능 명세서
https://sincere-nova-ec6.notion.site/495b3e7a2488442fa52a310af4f77766?pvs=4
---

### API 명세서
https://sincere-nova-ec6.notion.site/API-b216485c10e04126a28b0ce86bae203a?pvs=4
---

### 요구 사항 명세서
https://sincere-nova-ec6.notion.site/9058005fe69c4dbb926c547cf7a1f145?pvs=4
</details>
