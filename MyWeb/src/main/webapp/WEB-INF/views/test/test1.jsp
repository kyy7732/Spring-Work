<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <!-- 비동기 방식은 form을 사용하지 않는다 -->

    이름: <input type="text" name="name"> <br>

    나이: <input type="text" name="age"> <br>

    취미:
    <input type="checkbox" name="hobby" value="soccer"> 축구
    <input type="checkbox" name="hobby" value="music"> 음악감상
    <input type="checkbox" name="hobby" value="game"> 게임

    <button type="button" id="send">요청 보내기!</button>

    <script>

        const $sendBtn = document.getElementById('send');

        $sendBtn.onclick = function(e) { 
            const name = document.querySelector('input[name=name]').value; // input테그에 name이 'name'인 요소 지목
            const age = document.querySelector('input[name=age]').value;
            const $hobby = document.querySelectorAll('input[name=hobby]'); // NodeList라는 객체를 리턴한다

            const arr = []; // 체크가 된 요소값을 넣기 위한 배열.

            // querySelectorAll의 리턴값은 NodeList라는 유사 배열 형태
            // 배열의 메서드를 사용하기 위해 실제 배열로 변환하는 문법.
            [...$hobby].forEach($check => {
                if($check.checked) { // checked: 체크가 되어있으면 true, 아니면 false
                    arr.push($check.value);
                }

            }); // = for(let h of $hobby)(자바스크립은 :대신 of 사용)

            console.log(name);
            console.log(age);
            console.log(arr);

            // # http 요청을 서버로 보내는 방법
            // 1. XMLHttpRequest 객체를 생성.
            const xhr = new XMLHttpRequest();

            /*
            2. http 요청 설정 (요청 방식, 요청 URL) rest에서는 url이 간결해지기때문에 전송방식으로 대충 파악해야함
            - 요청 방식
            a. GET - 조회 
            b. POST - 등록
            c. PUT - 수정
            d. DELETE - 삭제
            */
            xhr.open('POST', '${pageContext.request.contextPath}/rest/object'); // (보낼 요청 방식, 요청 URL)

            // 3. 서버로 전송할 데이터를 제작합니다. (JSON은 언어가 아니고 표기법일뿐이다)
            // 제작하는 데이터의 형식은 JSON 형태여야 합니다. json은 자바스크립트가 아니고 표기법만 유사할 뿐이다.
            const data = {
                'name' : name,
                'age' : age,
                'hobby' : arr
            }; // 이 객체는 JSON이 아니라 JavaScript 객체이다. JS로 바로 보내면 JAVA에서 못 알아먹는다.

            // JS(자바 스크립트) -> JSON으로 변경: JSON.stringify(arg)
            const sendData = JSON.stringify(data);

            // 전송할 데이터의 형태가 어떠한지를 요청 헤더에 지정.
            xhr.setRequestHeader('content-type', 'application/json');

            // 4. 서버에 요청 전송
            xhr.send(sendData);
            
            // 5. 응답된 정보 확인
            xhr.onload = function() {
                // 응답 코드
                console.log(xhr.status);
                // 응답 데이터 확인
                console.log(xhr.response);
           }

        };

    </script>

</body>
</html>