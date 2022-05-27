package com.globalin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

// 컨트롤러에서 예외를 처리하는 방법
// @ControllerAdvice + @ExceptionHandler
// AOP ( Aspect Oriented Programming 관점 지향 프로그래밍)
// 공통된 관심사, 핵심적인 관심사 나눠서 프로그래밍
// 핵심적인 관심사 -> Controller 의 메소드 
// 공통된 관심사 -> Controller 의 모든 메소드에서 발생하는 예외 처리
// 원래대로라면 Controller 의 메소드들 마다 예외를 각각 처리해줘야 하지만 
// aop에서는 공통된 관심사를 가진 핵심적인 관심사 앞뒤에 붙어서 조립같은 일을 실행
// 핵심적인 관심사(메소드) 전에 공통적으로 실행이 필요한 부분 또는 후에 실행이 필요한 부분

@ControllerAdvice
// 스프링의 컨트롤러에서 발생하는 예외를 처리하는 클래스입니다.
public class CommonExceptionAdvice {
	private static Logger log = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	// 예외를 처리헤주는 메소드
	// ()안에 Exception.class -> 처리할 예외 타입 명시
	// RunTimeException.class 하면 런타임에러 처리하겠다!
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.error("Exception ...." + ex.getMessage());
		model.addAttribute("exception", ex);
		return "error_page";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) //
	public String hadle404(NoHandlerFoundException ex, Model model) {

		// return "파일 이름";
		return "custom404";
	}
}
