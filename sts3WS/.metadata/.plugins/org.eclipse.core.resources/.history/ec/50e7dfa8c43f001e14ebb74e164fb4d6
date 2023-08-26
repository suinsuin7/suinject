<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하하!!</title>
</head>
<body>
    <script>
        let mySocket = new WebSocket("ws://localhost:8080/zzang/ws-chat"); //client websocket

        function fOpen(){
            console.log("Ok Connected");
            mySocket.send("나는 한글 잘 되는데~");
        }

        function fMessage(){
            console.log("from server :", event.data);
        }

        ////event automatic
        mySocket.onopen = fOpen;
        mySocket.onmessage = fMessage;
        
        
    </script>

</body>
</html>