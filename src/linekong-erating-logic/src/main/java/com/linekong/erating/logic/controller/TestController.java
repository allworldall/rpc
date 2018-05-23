package com.linekong.erating.logic.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	
	@RequestMapping("/test")
	public void test(HttpServletResponse response) {
		try {
			//testService.test();
			response.getWriter().print("OK");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
