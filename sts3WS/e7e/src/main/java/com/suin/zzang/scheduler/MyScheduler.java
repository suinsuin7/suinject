package com.suin.zzang.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyScheduler {
	
	@Scheduled(fixedDelay = 3000)
	public void gangHate() {
		log.debug("배아파용");
	}
	
	@Scheduled(cron = "10 36 * * * *" )
	public void gangHate2() {
		log.debug("배아파용 36분 10초");
	}
}
