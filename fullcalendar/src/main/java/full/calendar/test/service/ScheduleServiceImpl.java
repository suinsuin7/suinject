package full.calendar.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import full.calendar.test.mapper.ScheduleMapper;
import full.calendar.test.vo.ScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	//플젝에서는 이거 말고 생성자 주입? 그거 쓰기
	@Autowired
	ScheduleMapper scheduleMapper;
	
	
	@Override
	public int insert(ScheduleVO schedule) {
		return scheduleMapper.insert(schedule);
	}

	@Override
	public List<ScheduleVO> findAll() {
		return scheduleMapper.findAll();
	}

	@Override
	public int delete(String start) {
		return scheduleMapper.delete(start);
	}

	@Override
	public int update(ScheduleVO schedule) {
		return scheduleMapper.update(schedule);
	}

}
