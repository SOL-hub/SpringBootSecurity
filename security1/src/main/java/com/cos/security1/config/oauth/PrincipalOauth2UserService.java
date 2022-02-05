package com.cos.security1.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	//구글로 부터 받은 userRequest데이터에 대한 후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getAttribute:"+userRequest.getClientRegistration());
		System.out.println("getAttribute:"+userRequest.getAccessToken().getTokenValue());
		
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		//구글로그인 버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code를 리턴(OAuth-Client라이브러리)-> AccessToken요청
		//userRequest정보 -> 회원프롵필 받아야함(loadUser함수) -> 회원프로필
		System.out.println("getAttribute : "+oauth2User.getAttributes());
			
		String provider = userRequest.getClientRegistration().getRegistrationId();//구글
		String providerId = oauth2User.getAttribute("sub"); //null값
		String username = provider+"_"+providerId;
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
				
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity ==null ) {
			System.out.println("구글 로그인이 최초입니다");
			userEntity = /*(User.builder())
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.build();*/
			userRepository.save(userEntity);
		}else {
			System.out.println("구글 로그인을 이미 한 적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
		}
	
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());//OAuth2User 리턴

}}
