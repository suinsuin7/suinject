package full.calendar.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScheduleVO {
	private int scheduleId;
	private String creatorId;
	private String scheduleDateTimeStart;
	private String scheduleDateTimeEnd;
	private String scheduleDateOlidTimeStart;
	private String scheduleDateOlidTimeEnd;
}
