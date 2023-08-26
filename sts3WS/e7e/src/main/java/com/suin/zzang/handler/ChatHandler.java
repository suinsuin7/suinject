package com.suin.zzang.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

//중계소 (엔드포인트 / ws-chat)로 오는 메시지를 처리해 주는 사람

// Jackson 라이브러리의 ObjectMapper를 이용
// Json <-> JAVA 변환을 편하게 사용하여 전송 데이터 구조를 잘 만들면
// 웬만한 필요한 건 다 할 수 있다!

@Slf4j
public class ChatHandler extends TextWebSocketHandler {
	private static List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("## 누군가 접속");
		list.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//String uMsg = message.getPayload();
		for (WebSocketSession webSocketSession : list) {
			webSocketSession.sendMessage(message);
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("## 누군가 떠남");
		list.remove(session);
	}
}