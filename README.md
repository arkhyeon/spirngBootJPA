# spirngBootJPA

- [[1] GetMapping, RequestMapping](#1-getmapping-requestmapping)
- [[2강] GetMapping2, PostMapping, PutMapping](#2강-getmapping2-postmapping-putmapping)
- [[3강] Delete Mapping](#3강-delete-mapping)
- [[4강] JsonInclude, 타입추론 var, Response 내려주기](#4강-jsoninclude-타입추론-var-response-내려주기)
- [[5강] Object Mapper](#5강-object-mapper)
- [[6강] Ioc, DI](#6강-ioc-di)
- [[7강] AOP 관점지향 프로그램 - 1](#7강-aop-관점지향-프로그램---1)
- [[8강] Object Mapper](#8강-object-mapper)
- [[9강] Spring Validation](#9강-spring-validation)
- [[10강] Spring Validation - 2 Custom Validation](#10강-spring-validation---2-custom-validation)
- [[11강] Exception Handler](#11강-exception-handler)
- [[12강] Exception Handler - 1](#12강-exception-handler---1)
- [[13강] Exception Handler - 2](#13강-exception-handler---2)
- [[14강] Lombok](#14강-lombok)
- [[15강] Filter](#15강-filter)
- [[16강] Interceptor - 1](#16강-interceptor---1)
- [[17강] Interceptor - 2](#17강-interceptor---2)
- [[18강] 비동기 Async](#18강-비동기-async)
- [[19강] RestTemplate 사용](#19강-RestTemplate-사용)
- [[복습] Naver Open Api 연동](#복습-Naver-Open-Api-연동)
- [[복습] WebClient feat.Naver Open Api](#복습-WebClient-feat.Naver-Open-Api)
- [[20강] JUnit](#20강-JUnit)

## [1] GetMapping, RequestMapping

1. path-variable   
 http://localhost:8081/api/get/pathVariable/{name}  
 @GetMapping("/path-variable/{tech}")  
 @PathVariable(name = "tech") String pathTech


## [2강] GetMapping2, PostMapping, PutMapping

1. @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)  
   \> @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)  
   교착 상태가 일어나는 버그 발생으로 PropertyNamingStrategy 아래 모든 클래스 사용 중지  
   따라서 PropertyNamingStrategies 인스턴스용 컨테이너 추가(취소선 발생 X)  

2. @JsonProperty("car_number")  
    - 요청의 키와 응답의 키가 다를 때 사용  
    - 요청 : car_number >> 응답 : carNumber  


## [3강] Delete Mapping  

 1. 200 ok 확인  

## [4강] JsonInclude, 타입추론 var, Response 내려주기

1. @JsonInclude(JsonInclude.Include.NON_NULL)  
    - Response 에 내려줄 값 확인(null인 값은 내리지 않는다)

2. 타입추론 var  
    - `User` user > `var` user = new User();  
    - 초기화 값이 있는 지역변수로만 선언 가능  
    - 변수명에 집중 가능  
 | Map<String, Integer> map = new HashMap<>();  
 | var map = new HashMap<String, Integer>();  
    - 런타임 오버헤드 X  
 | var i = 3으로 지정 시 i는 int로 지정 됨.  
 | i = "String" 불가능  
 
## [5강] Object Mapper

1. Object Mapper  
    - text json <> object

2. object > text  
    - object mapper는 get method 활용(참조 클래스에 getter 추가)  
   - 또한 참조 클래스에 get으로 시작하는 method 작성 시 오류(get을 보고 찾아옴)

3. text > object  
    - default 생성자 필요  

   \* 클래스 내의 함수 [메소드]

## [6강] Ioc, DI

1. Ioc 
   - 스프링에서 일반적인 자바 객체를 new 로 생성하여 개발자가 관리하는 것이 아닌 Spring Container에 모두 맡긴다.
    - 즉 개발자에서 프레임워크로 객체 관리의 권한이 넘어감(제어의 역전)

2. DI(Dependency Injection)
    - 스프링이 알아서 객체의 생명주기 관리 
    - 객체를 관리하기위해 주입 받음 

3. 장점
    - 의존성으로 부터 격리시켜 코드 테스트 용이
    - DI 통해 불가능한 상황을 Mock와 같은 기술로 안정적 테스트
    - 코드를 확장, 변경 시 영향 최소화(추상화)
    - 순환참조를 막음

## [7강] AOP 관점지향 프로그램 - 1

0. Dependency
 - implementation 'org.springframework.boot:spring-boot-starter-aop'

1. AOP
 - 스프링은 대부분 MVC 웹 어플리케이션에서는 3가지 Layer로 정의
 - Web Layer : RestAPI 제공 Client 중심의 로직 적용
 - Business Layer : 내부 정책에 따른 로직을 개발, 주로 해당 부분 개발
 - Data Layer : 데이터 베이스 및 외부 연동 처리

2. Annotation
 - Aspect : 부가 기능 모듈화
 - JoinPoint : 메소드의 정보
 - Before : 메소드 실행 전
 - After : 메소드 실행 후
 - After-returning : 메소드가 정상적으로 실행된 후
 - After-throwing : 예외가 발생한 후
 - Around : 메소드 호출 이전, 이후, 예외발생 등 모든 시점
 - Component : 직접 작성한 Class를 Bean으로 등록

## [8강] Object Mapper

1. Jackson(Java 라이브러리)
 Json > Java Object 변환
 Java Object > Json 변환
 
2. Json Node
 - Key : Value / 값 변경 불가
 - Key 값을 통해 값을 가져옴

3. ObjectNode
 - Key : Value / 값 변경 가능
 
4. ArrayNode
 - [value1, value2, ...] / 값 변경 가능
 
5. Getter
 - 기본
 ```
 String _name = jsonNode.get("name").asText();
 ```
 - List
 ```
 JsonNode cars = jsonNode.get("cars");
           ArrayNode an = (ArrayNode)cars;
   
           List<Car> _cars = om.convertValue(an, new TypeReference<List<Car>>() {});
 ```
           
 6. Setter
 ```
 ObjectNode on = (ObjectNode) jsonNode;
         on.put("name", "steve");
         on.put("age", 20);
 ```
         
 7. Json Data 출력
 ```
 ObjectNode.toPrettyString()
 ```

## [9강] Spring Validation

- spring-boot-starter-validation

1. Validation
 - @Size                : 문자 길이 측정(int 불가)
 - @NotNull             : null 불가
 - @NotEmpty            : null, 공백 불가
 - @NotBlank            : null, 공백, " "(빈값) 불가
 - @Past                : 과거 날짜
 - @PastOrPresent       : 오늘, 과거
 - @Future              : 미래
 - @FutureOrPresent     : 오늘, 미래
 - @Pattern             : 정규식 검증
 - @Max                 : 최대값
 - @Min                 : 최소값
 - @??(regexp = "정규식", message = "오류 메세지")
 - @AssertTrue / False  : 별도 Logic 적용
 ```
    @AssertTrue(message = "yyyyMM형식에 맞지 않습니다") // return true면 정상 false면 비정상
    public boolean isReqYearMonthValidation() {
        try {
            LocalDate localDate = LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        }catch (Exception e){
            return false;
        }


        return  true;
    }
```
 - @Valid               : 해당 Object Validation 실행 정의
    - public Object user(@Valid @RequestBody User user){


- 해당 오류 Return
```
BindingResult 사용 
filed.getFiled 어디서 오류? 
objectError.getDefaultMessage() 오류메세지

public Object user(@Valid @RequestBody User user, BindingResult br){

        if(br.hasErrors()){
            StringBuilder sb = new StringBuilder();
            br.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String msg = objectError.getDefaultMessage();
                System.out.println(field.getField());
                System.out.println(msg);

                sb.append("filed : " + field.getField());
                sb.append("msg : " + msg);

            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
}

```
## [10강] Spring Validation - 2 Custom Validation

1. Custom Validation
 - 한 클래스 내에서 만들어서 사용하면 다른 클래스에서도 만들어야 하는 중복 코드 발생   
   \> Custom Validation Annotation 생성의 이유
   
 EX) @YearMonth 
 - validation annotation 생성
   기본 값, 메세지 설정
     - @Constraint  
       validatedBy 정의할 validator 전달 시 Spring Boot에서 API를 호출할 때 전달한 값을 가져와 validation 수행
     - @Target   
       Java compiler가 annotation이 어디에 적용될지 결정
     - @Retention  
       Annotation이 실제 적용, 유지되는 범위
 - validator YearMonthValidator 생성
 
2. List<Car> Class
 - @Valid 사용하여야 내부 Validation 작동
 
3. return type Boolean은 메소드 명 is 시작 
 
 ## [11강] Exception Handler

1. 에러 시 나오는 문장 수정
 - 400 Bad Request
  \> default message [비어 있을 수 없습니다]]

2. @RestControllerAdvice(Global 설정)
 - 내부에 @ExceptionHandler 설정
 - 예외 캐치 후 핸들링 기능 수행
 - @ResponseBody 통해 객체 리턴
 - @RestControllerAdvice("com.example.demo.login.controller")  
  \> 패키지 단위 핸들링

3. @ExceptionHandler(Rest/Controller 설정)
 - @ExceptionHandler(value = [원하는 Exception Class 설정])
 - Global로 지정 시에도 Controller에 지정한 ExceptionHandler 우선 순위

4. Validated
 - 검증은 컨트롤러에서 처리하는게 좋지만 AOP 기반 메소드 요청을 가로채 검증 진행 제공
 - 제약 조건 어노테이션에 조건이 적용될 검증 그룹 지정하여 적용
 - 클래스 레벨에 검증 인터셉터 등록
 - AOP : 로직 기준 핵심적 관점, 부가적 관점 나누어 관점 기준 각각 모듈화

5. ConstraintViolationException : 제약조건
6. MissingServletRequestParameterException : 필수 파라미터 결여

## [12강] Exception Handler - 1

1. Validated
 - 검증은 컨트롤러에서 처리하는게 좋지만 AOP 기반 메소드 요청을 가로채 검증 진행 제공
 - 제약 조건 어노테이션에 조건이 적용될 검증 그룹 지정하여 적용
 - 클래스 레벨에 검증 인터셉터 등록
 - AOP : 로직 기준 핵심적 관점, 부가적 관점 나누어 관점 기준 각각 모듈화

2. ConstraintViolationException : 제약조건
3. MissingServletRequestParameterException : 필수 파라미터 결여
 
## [13강] Exception Handler - 2

1. ErrorResponse
 - DTO > Error, ErrorResponse 작성
 
 기존 의도를 알 수 없는 Error를 내려줬다면
 ErrorResponse를 이용해 아래와 같이 정확한
 에러를 받을 수 있다.
 ```
 {
     "statusCode": "400 BAD_REQUEST",
     "requestUrl": "/api/user",
     "code": null,
     "message": "",
     "resultCode": "FAIL",
     "errorList":[
         {
             "field": "name",
             "message": "크기가 1에서 10 사이여야 합니다",
             "invalidValue": "size1to10oversize"
         },
         {
             "field": "age",
             "message": "1 이상이어야 합니다",
             "invalidValue": "0"
         }
     ]
 }
 ```
 
 ## [14강] Lombok

 - Java 라이브러리 dto의 getter, setter, toString 등의 메서드 작성을 어노테이션으로 대체
 - compileOnly: compile 시에만 빌드하고 빌드 결과물에는 포함하지 않음

1. @Data
 - @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 포함

2. @NoArgsConstructor
 - 기본 파라미터 없는 생성자

3. @AllArgsConstructor
 - 모든 필드 값을 파라미터로 받는 생성자
 
4. @Slf4j
 - 로깅 프레임 워크에 대한 추상화(인터페이스) 라이브러리

 ## [15강] Filter
 - 요청이 전달 전, 후 작업 처리 가능
 
1. ContentCachingRequestWrapper / ContentCachingResponseWrapper  
   -  생성이유 :  HttpServletRequest의 InputStream은 한 번만 읽고 그 이후는 IOException 발생
    - HttpServletRequestWrapper(HttpServletRequest 인터페이스의 편리한 구현을 제공)의 구현체  
    입력 스트림에서 읽은 모든 콘텐츠를 캐시하고 바이트 배열을 통해 콘텐츠를 검색
    - Spring Project에서 직접 관리하기에 안정성 향상

2. Implements Filter Method 3

    - init()   
      - Web Container(Tomcat) 시작 시 필터 객체 생성, 이때 객체가 생성되면서 최초 한 번 호출
      - FilterConfig 객체로 설정값 받아 작업 시 필요한 객체를 초기화에 사용

    - destroy()  
       - 필터 객체가 제거될 때 실행 
       - 초기화 시 생성했던 자원 제거 및 종료

    - dofilter(ServletRequest  request ,ServletResponse response , FilterChain chain)  
 
       - chain.doFilter() 메서드의 전후로 전처리 / 후처리  
       - 전처리 : Dispatcher-Servlet 통해 사용 전 처리할 작업은 chain.doFilter() 전 정의 
       - 후처리 : Dispatcher-Servlet 의해 사용 후 처리할 작업은 chain.doFilter() 후 정의 
       - copyBodyToResponse : 후처리에서 로깅 시 Body 값을 한 번만 읽을 수 있기에 캐싱해둬야 사용자 response View 가능

 @Component : 직접 작성한 Class를 Bean 등록

 ## [16강] Interceptor - 1

 - Interceptor(인증) / Filter(로그) 매우 유사
 - Spring Context(Bean 활용 및 추가적인 기능 제공)에 등록
 - AOP와 유사한 기능(로직 기준 핵심적 관점, 부가적 관점으로 나누어 그 관점 기준 각각 모듈화)
 - 선/후 처리 함으로 써 비지니스 로직과 분리한다.
 
1. @RequiredArgsConstructor(생성자 주입)
 - 초기화 되지않은 final 필드, @NonNull 필드에 생성자 생성(의존성 주입)
 - 순환 참조 방지를 위해 @Autowired(필드 주입) 대신 사용
 - 생정자 주입 : 먼저 빈을 생성하지 않고 주입하려는 빈을 먼저 찾음
 
2. @Retention 어느 시점까지 어노테이션의 메모리를 가져갈 지 설정   
   @Target 필드, 메소드, 클래스, 파라미터 등 선언할 수 있는 타입을 설정

## [17강] Interceptor - 2

1. CustomException 작성
2. Interceptor 에 throw new CustomException 작성
3. GlobalExceptionHandler @ControllerAdvice는, @ExceptionHandler(CustomException.class) 작성

4. @ExceptionHandler
  - @Controller, @RestController 적용된 Bean 내 발생 예외를 하나의 메서드에서 처리
5. @ControllerAdvice
  - 전역 발생 예외 처리
 
## [18강] 비동기 Async

1. @EnableAsync
 - 비동기 기능을 활성화

2. CompletableFuture  
 - Future  
 서로 다른 실행시간을 가지는 Future 들을 조합해서 계산, 다른 결과와 같이 계산 등 복잡한 로직을 다루기 힘듦  
 - CompletionStage  
 계산의 완료는 단일 단계의 완료 뿐만 아니라 다른 여러 단계 혹은 다른 여러 단계 중의 하나로 이어질 수 있음도 포함  
 또한 각 단계에서 발생한 에러를 관리하고 전달  
 
 - Future 와 CompletionStage 를 구현한 클래스  
 
3. ThreadPoolTaskExecutor
 - setMaxPoolSize(100) : 쓰레드 풀의 최대 사이즈
 - setCorePoolSize(10) : 동시에 실행시킬 쓰레드의 개수
 - setQueueCapacity(10) : 쓰레드 풀 큐의 사이즈 corePoolSize 개수를 넘어서는 task 진입 시 queue에 해당 task들 누적 최대로 maxPoolSize 개수 만큼 가능
 - setThreadNamePrefix("Async-") : Prefix 설정
 - 실행도 : core 10개 사용 > queue 10개 누적 > core 10개 사용 > queue 10개 누적 반복 > 100개 Max Size

 ## [19강] RestTemplate 사용

 - Client / Server 통신
 - 스프링에서 제공하는 http 통신에 유용하게 쓸 수 있는 템플릿
 
 1. UriComponentsBuilder  
                .fromUriString("http://localhost:9090")  
                .path("/api/server/hello") 
                .encode()  
                .queryParam("name", "aaaa")  
                .queryParam("age", 99)  
                .build()  
                .toUri();  
                
2. ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

 - 반환 타입 result = restTemplate.반환 타입
 
 getForObject / GET /    
 주어진 URL 주소로 HTTP GET 메서드로 객체로 결과 반환
 
 getForEntity / GET  
 주어진 URL 주소로 HTTP GET 메서드로 결과는 ResponseEntity 반환
 
 postForLocation / POST
 POST 요청을 보내고 결과로 헤더에 저장된 URI를 결과 반환
 
 postForObject / POST
 POST 요청을 보내고 객체로 결과 반환
 
 postForEntity / POST  
 POST 요청을 보내고 결과로 ResponseEntity 반환
 
 delete / DELETE  
 주어진 URL 주소로 HTTP DELETE 메서드 실행
 
 headForHeaders / HEADER  
 헤더의 모든 정보를 얻을 수 있으면 HTTP HEAD 메서드 사용
 
 put / PUT  
 주어진 URL 주소로 HTTP PUT 메서드 실행
 
 patchForObject / PATCH  
 주어진 URL 주소로 HTTP PATCH 메서드 실행
 
 optionsForAllow / OPTIONS  
 주어진 URL 주소에서 지원하는 HTTP 메서드 조회
 
 exchange / any  
 HTTP 헤더를 새로 만들 수 있고 어떤 HTTP 메서드도 사용 가능
 
 execute / any  
 Request/Response 콜백을 수정 가능

 - EXCHANGE
 
 - HTTP 헤더를 새로 만들 수 있고 어떤 HTTP 메서드도 사용 가능
 - 지정된 URI 템플릿에 대해 HTTP 메서드를 실행하여 지정된 요청 엔터티를 요청에 쓰고 응답을 ResponseEntity 반환 
 - URI 템플릿 변수는 주어진 URI 변수가 있는 경우 이를 사용하여 확장
 
 1. Generic Type
  - 데이터 타입 일반화
  - 클래스/메소드에서 사용할 내부 데이터 타입을 컴파일 시 미리 지정
  - 코드 작성 후 코드를 다양한 타입의 객체에 대하여 재사용하는 프로그래밍 기법
  - 클래스에서 사용할 타입을 클래스 외부에서 설정하는 타입
  - 클래스/메소드 내부에서 사용되는 객체의 타입 안정성
  - 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄임
  
 2. ParameterizedTypeReference
  - generic 타입으로 응답
  
 3. HttpEntity 이후 response 응답 못받음.
  - 이미 사용했기에

 
 https://www.notion.so/8c69a39a82054091bdfabc72c127eb51

 ## [복습] Naver Open Api 연동

1. UriComponentsBuilder
 - Controller에서 addAttribute로 각각 속성을 지정해주지 않아도 이 class를 이용하면 쉽게 파라미터들 전달
 - Spring 3부터 지원, REST API 호출이후 응답을 받을 때까지 기다리는 동기 방식
2. fromUriString
 - 주어진 URI 문자열로 초기화되는 빌더를 작성하십시오.
3. path
 - 빌더의 경로에 추가합니다.
4. queryParam
 - 쿼리파라미터 키밸류
5. encode
 - 자동으로 UTF-8로 인코딩 해준다.
6. build
 - 인스턴스 빌드
7. toUri
 - 인스턴스에서 URI를 만듭니다
 - Uniform Resource Identifier
 - 인터넷에 있는 자원을 나타내는 유일한 주소
 
 ## [복습] WebClient feat.Naver Open Api

1. API 호출 Http Client 모듈
2. RestTemplate은 Blocking방식이고, WebClient는 Non-Blocking방식
 - Blocking : 요청하고 응답 올때까지 기다리는 방식
 - Non-Blocking : 요청 후 응답신호가 오면 그 때 결과 처리
 - 동기/비동기 : Blocking, Non-Blocking 차이
   - 호출되는 함수가 바로 리턴하는지 : 호출되는 함수의 작업 완료 여부를 누가 신경쓰는지
3. 요청자와 제공자 사이의 통신을 좀 더 효율적인 Non-Blocking방식으로 하기 위해서입니다. 


2. Flux / Mono
 - Flux : 0 ~ N 개의 데이터를 전달
 - Mono : 0 ~ 1 개의 데이터를 전달
 - 보통 여러 스트림을 하나의 결과는 Mono, 각각의 Mono를 합쳐서 하나의 여러 값을 처리할 떄 Flux
 - Mono/Flux는 Publisher 인터페이스를 구현해서 만듦
 - 하나의 결과를 List에 담지 않기에 Mono, Flux 존재
 
 ## [20강] JUnit

1. TDD
 - 테스트 주도개발 
 - 유지 보수 및 운영 환경 에러를 방지하기 위해 단위별로 검증하는 테스트 프레임워크
2. 단위테스트
 - 작성한 코드가 원하는 대로 동작하는지 검증

3. JUnit
 - 자바 유닛 테스트 프레임워크

4. @Test
 - 테스트 수행하는 메소드 지정
 - 각각의 테스트가 서로 영향을 주지 않고 독립적으로 실행되는 것을 지향


함수는 하나의 행동만 / 작게 더 작게
