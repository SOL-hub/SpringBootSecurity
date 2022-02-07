package com.sol.security2.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{  

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
      MustacheViewResolver resolver = new MustacheViewResolver(); //오버라이딩해서 재설정

      resolver.setCharset("UTF-8");//내가 너한테 던질 파일은 
      resolver.setContentType("text/html;charset=UTF-8"); //이거야.
      resolver.setPrefix("classpath:/templates/"); //경로의 
      resolver.setSuffix(".html");//이러면 머스태치가 인지를 한다.

      registry.viewResolver(resolver);
  }
}
