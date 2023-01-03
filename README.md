# 스프링 부트 게시판 구현
## Thymeleaf 속성

|이름|설명|
|---|---|
|th:each|반복하려는 html 엘리먼트에 사용하여 콜렉션(Collection)을 반복 <br> th:each = "콜렉션 변수명, status 변수명 : ${리스트}"|
|th:with|변수 형태의 값을 재정의 <br> th:with = 변수명=${data}|
|th:text|document 객체에 텍스트 삽입 <br> th:text = ${data}|
|th:href|href 에 값을 삽입 <br> th:href = @{URL(파라미터명=${data})}|
|th:value|value 에 값을 삽입 <br> th:value = ${data}|

|이름|설명|
|---|---|
|th:if <br> th:unless|조건이 참 또는 거짓이면 해당 태그를 노출한다. <br><br> (span th:if="${data} == 'data'")(/span) <br> (span th:unless="${data} == 'data'")(/span) <br><br> <u>※ th:unless if 의 조건과 unless 의 조건을 동일하게 적어야 동작한다.</u> <br><br> th:if 단독으로도 사용 가능하다.|
|th:classappend|조건이 참이면, 거짓이면 해당 클래스를 추가한다. <br> (a th:classappend="${data == 'data'} ? bg-primary : bg-secondary")(/a)|
