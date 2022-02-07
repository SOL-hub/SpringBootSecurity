package com.sol.security2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"","/"})//주소를 두개 넣기
	public String index() {
		//머스테치 기본폴더 src/main/resources/
		//뷰리졸버 설정 시 : templates를 prefix로 잡고 , .mustache를 suffix로 잡아 세팅한다.
		return "index";//src/main/resources/templates/index.mustache
	} 
	

}
