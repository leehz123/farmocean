package com.ezen.farmocean.chat.handler;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ezen.farmocean.member.dto.LoginMember;

@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler {
	// 세션 리스트
	private List<String> accessMemberList = new ArrayList<>();
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	// 클라이언트가 연결 되었을 때 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LoginMember senderId = (LoginMember) session.getAttributes().get("loginId");
		
		if(accessMemberList.contains(senderId.getMember_id())){
			System.out.println("중복 접속입니다");
			System.out.println(accessMemberList);
			
		} else {
			accessMemberList.add(senderId.getMember_id());
			sessionList.add(session);
			for(int i =0 ; i < sessionList.size();++i) {
				WebSocketSession s= sessionList.get(i);
				
				s.sendMessage(new TextMessage("["+senderId.getMember_nickName()+"님이 입장했습니다.]"));
			}
		}
		
		
	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LoginMember senderId = (LoginMember) session.getAttributes().get("loginId");
		
		logger.info(senderId.getMember_id());
		
		logger.info("{}로 부터 {} 받음", senderId.getMember_nickName(), message.getPayload());
		// 모든 유저에게 메세지 출력
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(senderId.getMember_nickName()+" : "+message.getPayload()));
		}
	}

	// 클라이언트 연결을 끊었을 때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LoginMember senderId = (LoginMember) session.getAttributes().get("loginId");
		accessMemberList.remove(senderId.getMember_id());
		System.out.println(accessMemberList);
		logger.info(senderId.getMember_id());
		sessionList.remove(session);
		logger.info("{} 연결 끊김.", senderId.getMember_nickName());
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String chatTest(Locale locale, Model model) {
		
		return "chat/echo";
	}
}