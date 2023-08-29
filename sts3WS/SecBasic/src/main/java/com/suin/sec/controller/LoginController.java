package com.suin.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/myLogin")
    public String myLogin() {
        return "mylogin";

	}
}
