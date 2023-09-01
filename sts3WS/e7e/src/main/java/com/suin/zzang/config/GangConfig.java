package com.suin.zzang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration // root=context에서 annotation-config
@EnableScheduling // 기본 제공 스케줄러 사용하겠다, xml로(task어쩌구)
public class GangConfig {

}
