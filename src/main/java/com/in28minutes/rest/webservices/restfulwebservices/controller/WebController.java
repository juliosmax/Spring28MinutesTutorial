package com.in28minutes.rest.webservices.restfulwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.helloworld.*;

@RestController
public class WebController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hoolaa")
	public String method() {
	  return "hola desde eclipse 4";	
	}
	
	@GetMapping("helloWorldBean")
	public HelloWorldBean helloWorldBean() {
	   return new HelloWorldBean("hello world");
	}
	
	@GetMapping("helloWorldBean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanWithPathVariable(@PathVariable String name) {
	   return new HelloWorldBean(String.format("hello world, %s ", name));
	}
	
	/*@GetMapping("/hoolaa-internationalized")   Comentamos ese metodo porque no queremos usar el parametro con el @RequestHeader
	public String methodWithInternationalization(@RequestHeader(name="Accept-language", required=false) Locale locale) {
	  return messageSource.getMessage("good.morning.message", null, "Default Message" ,locale);
	}*/
	
	@GetMapping("/hoolaa-internationalized")
	public String methodWithInternationalization() {
	  return messageSource.getMessage("good.morning.message", null, "Default Message" ,LocaleContextHolder.getLocale());
	}
	
}