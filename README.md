# 스프링 부트 게시판 구현
## Thymeleaf 속성

|이름|설명|
|---|---|
|th:each|반복하려는 html 엘리먼트에 사용하여 콜렉션(Collection)을 반복 <br> <span style="color:red">th:each = "콜렉션 변수명, status 변수명 : ${리스트}"</span>|
|th:with|변수 형태의 값을 재정의 <br> <span style="color:red">th:with = 변수명=${data}</span>|
|th:text|document 객체에 텍스트 삽입 <br> <span style="color:red">th:text = ${data}</span>|
|th:href|href 에 값을 삽입 <br> <span style="color:red">th:href = @{URL(파라미터명=${data})}</span>|
|th:value|value 에 값을 삽입 <br> <span style="color:red">th:value = ${data}</span>|
