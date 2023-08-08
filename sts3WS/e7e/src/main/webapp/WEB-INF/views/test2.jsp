<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>미지의 세계</h1>
<script>    
function fPostAjax1() {
    let testVO = {
        "name": "susu",
        "age": "27",
        "alias": "gg"
    };

    let xhr = new XMLHttpRequest();
    xhr.open("delete","/zzang/suin/rain",true);
    //보내는 데이터가 json 형태의 문자열임을 알려줘야 함!!!
    xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");    
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            console.log(JSON.parse(xhr.responseText));
        }
    }
    // get방식 이외에는 보내는 데이터를 send()안에 매개변수로 보내야 함
    xhr.send(JSON.stringify(testVO)); // 꼬옥 문자열로
}
fPostAjax1();

function fPostAjax() {
    let myList = [
        "su",
        "27",
        "gg"
    ];

    let xhr = new XMLHttpRequest();
    xhr.open("post","/zzang/suin/rain",true);
    //보내는 데이터가 json 형태의 문자열임을 알려줘야 함!!!
    xhr.setRequestHeader("Content-Type", "application/json");    
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            console.log(JSON.parse(xhr.responseText));
        }
    }
    // get방식 이외에는 보내는 데이터를 send()안에 매개변수로 보내야 함
    xhr.send(JSON.stringify(myList)); // 꼬옥 문자열로
}
fPostAjax();


</script>
</body>
</html>