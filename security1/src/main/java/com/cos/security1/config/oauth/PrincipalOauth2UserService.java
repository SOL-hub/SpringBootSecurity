package com.cos.security1.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//구글로 부터 받은 userRequest데이터에 대한후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest:"+userRequest.getClientRegistration());
		System.out.println("userRequest:"+userRequest.getAccessToken().getTokenValue());
		//구글로그인 버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client라이브러리)-> AccessToken요청
		//userRequest정보 -> 회원프롵필 받아야함(loadUser함수) -> 회원프로필
		System.out.println("getAttribute : "+super.loadUser(userRequest).getAuthorities());
		
		
		
		OAuth2User oauth2User = super.loadUser(userRequest);
				//회원가입을 강제로 진행해볼 예정
		return super.loadUser(userRequest);
	
	//회원가입을 강제로 진행해볼 예정
		//	if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
		//		System.out.println("구글 로그인 요청");
		//		oAuth2UserInfo=new GoogleUserInfo(oauth2User.getAttributes());
		//	}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
		//		
		//	}else {
		//		System.out.println("우리는 구글과 페이스북만 지원해요");
		//	}
}}
