package ch.ralscha.e4ds.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;

@Service
public class PollService {
	
	private DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
	
	@ExtDirectMethod(value=ExtDirectMethodType.POLL, event = "chartdata")
	@Transactional
	public Poll getPollData() {		
		return new Poll(fmt.print(new DateTime()), (int)(Math.random()*1000));
	}
}
