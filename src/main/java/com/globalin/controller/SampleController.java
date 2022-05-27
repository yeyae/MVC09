package com.globalin.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globalin.model.Sample;
import com.globalin.model.SampleList;
import com.globalin.model.Todo;

//스프링에서는 @WebServlet 역할을 Controller가 대신함
//Controller 도 생각해보면 우리가 웹프로젝트를 실행하기 위해 필요한 객체
// 스프링을 사용하면 객체를 스프링에게 맡김. controller도 스프링의 빈으로 관리가 된다.
//component 대신 MVC 패턴에서 controller 역할을 하는 component란 뜻에서 컨트롤러 사용
//servlet-context.xml에서 basePackgaes에서 주소를 줌으로써 ComponentScan 에서 대상이 된다.

@Controller
@RequestMapping("/sample/*")
public class SampleController {
	// 이 클래스는 이제 main 에서 부를 필요가 없음
	// 웹프로젝트는 동작을 웹에서 하는데 웹에서 주고받기 때문에
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	// Parameter 의 변환을 위해 필요한 메소드 정의
	// Parameter 를 수집해서 가져 오는 것 -> 바인딩
	// 변환이 쉬운 데이터는 자동으로 변환되나 어려운 데이터(날짜)는 java.util.date 타입으로 변환하는 작업 필요
	// Controller 가 파라미터를 변환 해주도록 처리하게 하는 메소드
//	@InitBinder
//	public void initBinder(WebDataBinder wdb) {
//		// 파라미터를 변환해주는 작업
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		// null 허용 안함->false
//		wdb.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}

	// 메소드 위에 RequestMapping 설정을 해주면
	// 클래스의 기본 RequestMapping + 메소드의 RequestMapping 설정

	// basicGet이라는 메소드는 /basic이라는 경로로 들어온 get+post 요청 처리
	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		logger.info("basic 경로 요청입니다.");
	}

	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		logger.info("basic Get 2 경로 요청입니다.");

	}

	@GetMapping("/ex01")
	public String ex01(Sample sample) {
		logger.info(" " + sample);
		// Sample sp=new Sample();
		// String name = req.getParameter("name");
		// int age=~~~
		// sp.setAge(age);

		return "ex01";
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		logger.info("이름 : " + name);
		logger.info("나이 : " + age);
		return "ex02";
	}

	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		logger.info("ids : " + ids);
		return "ex02List";
	}

	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		logger.info("array ids : " + Arrays.toString(ids));
		return "ex02arry";
	}

	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleList list) {
		logger.info("sample list:" + list);
		return "ex02Bean";
	}

	@GetMapping("/ex03")
	public String ex03(Todo todo) {
		logger.info("할 일 : " + todo);
		return "ex03";
	}

	// Model 데이터 전달자
	// 컨트롤러에서 메서드를 작성할 때 특별하게 사용이 가능한 Model 이라는 타입이 존재
	// Model 객체는 JSP에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 하는 존재
	// 메서드에서 JSP와 같은 뷰로 전달해야하는 데이터를 담아서 보내고 메서드의 파라미터에 Model 타입이 지정된경우 스프링이 객체를 만들어서
	// 메서드에 주입함
	@GetMapping("/ex04")
	public String ex04(Sample sample, @ModelAttribute("page") int page) {
		// @ModelAttribute("page")지우면 안 나옴
		// 웹페이지의 구조는 Request에 전달된 데이터를 가지고 화면에 전달함
		// SPring의 Controller는 기본적으로 Java Beans 규칙에 맞는 객체만 화면에 전달
		// Java Beans 규칙? 생성자, getter/setter를 가진 클래스 객체
		// 기본형 데이터 타입을 화면으로 전달할 때 기본형은 규칙에 맞지 않으므로 Spring이 전달하지 않는다.
		// 이때 우리가 기본형 데이터를 넘기고 싶다면 @ModelAttribute("이름") 추가
		logger.info("sample : " + sample);
		logger.info("page :" + page);
		return "/sample/ex04";
	}

	@GetMapping("/ex040")
	public String ex040(Model model) {
		model.addAttribute("serverTime", new java.util.Date());
		return "/sample/ex04";
	}

	/*
	 * RedirectAttributes Model 타입과 비슷한 spring이 데이터를 전달해주는 타입 중 하나 일회성으로 데이터를 전달하는
	 * 용도. 다음에 이동하는 페이지 한 번만 사용하고 이후에 사용 못함
	 * response.sendRedirect("/home?name=sss&age=10"); 이걸 spring에서는
	 */
	@GetMapping("/ex041")
	public String ex041(RedirectAttributes rttr) {
		rttr.addFlashAttribute("name", "jinooooo");
		rttr.addFlashAttribute("age", 10);
		return "redirect:/sample/ex040";
	}

	/*
	 * Controller 메소드 리턴 타입 / String : JSP 이용하는 경우 jsp 파일 경로, 파일 이름 나타냄 / void : 호출한
	 * url과 동일한 이름을 가진 jsp 파일 이름을 나타냄 / ResponseEntity : response 할 때 헤더 정보와 내용을 가공할
	 * 수 있도록 한다. Model : 모델로 데이터 전달, ModelAndView : 모델로 데이터 전달도 가능 그리고 view 지정도
	 * 가능(사용자에게 보여줄 view 파일 이름) // Object (우리가 만든 Model 클래스) : 주로 JSON 타입의 데이터로 처리할
	 * 때 사용
	 */
	@GetMapping("/ex06")
	public @ResponseBody Sample ex06() {
		Sample sample = new Sample();
		sample.setName("jinwoo");
		sample.setAge(20);
		return sample;
	}

	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		String msg = "{\"name : \"jinwoo\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		// 우리가 보내는 데이터 타입이 json 타입이고, 인코딩이 UTF-8이다.
		// 라고 헤더에 적어서 보낸다. 브라우저가 데이터 타입, 인코딩을 알게 되서 형식에 맞게 출력
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}

	@GetMapping("/exUpload")
	public void exUpload() {
		logger.info("/exUpload");
	}

	@PostMapping("/exUploadPost")
	public void exexUploadPost(ArrayList<MultipartFile> files) {
		for (MultipartFile file : files) {
			logger.info("--------------");
			logger.info("파일 이름 : " + file.getOriginalFilename());
			logger.info("파일 크기 : " + file.getSize());
		}

	}
}
