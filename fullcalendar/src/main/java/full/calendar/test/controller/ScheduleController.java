package full.calendar.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import full.calendar.test.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/full-calendar")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping("/main")
	public String main() {
		return "index";
	}
	
	//이벤트 조회
	
}
