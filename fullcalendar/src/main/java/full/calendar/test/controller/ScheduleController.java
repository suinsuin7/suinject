package full.calendar.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import full.calendar.test.service.ScheduleService;
import full.calendar.test.vo.ScheduleVO;
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
	// 이벤트 조회
		@GetMapping("/calendar-admin-update")
		@ResponseBody
		public List<Map<String, Object>> showAllEventInUpdate() throws Exception{
			
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			
			HashMap<String, Object> hash = new HashMap<>();
			
			List<ScheduleVO> list = scheduleService.findAll();
			
			for(ScheduleVO schedule : list) {
				hash.put("id", schedule.getScheduleId());
				hash.put("title", schedule.getCreatorId());
				hash.put("start", schedule.getScheduleDateTimeStart());
				hash.put("end", schedule.getScheduleDateTimeEnd());
				
				jsonObj = new JSONObject(hash);
				jsonArr.add(jsonObj);
			}
			log.info("jsonArrCheck:{}",jsonArr);
			return jsonArr;
		}
	
}
