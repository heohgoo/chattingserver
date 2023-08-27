# chattingserver
<br>

Chatting App

Netty를 이용하여 Springboot와 MongoDB로 구축하는 채팅 서버

`Springboot - v.2.7.2`

`MongoDB - v.7.0`


Netty를 이용하므로 서블릿 기반의 멀티 스레드와는 달리 1개의 스레드만을 이용하므로 컨텍스트 스위칭이 일어나지 않아 채팅 서버를 구축하는 데 부합하다.

요청과 응답이 완료되면 연결이 끊어지는 http 프로토콜이 아닌, 응답이 계속 유지될 수 있는 `SSE 프로토콜`을 사용한다.



