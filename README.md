# spirngBootJPA

1. [GetMapping, RequestMapping](#[1강])
2. [GetMapping2, PostMapping, PutMapping](#[2강])

## [1강] GetMapping, RequestMapping

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
