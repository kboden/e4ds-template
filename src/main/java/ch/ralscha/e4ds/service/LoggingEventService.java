package ch.ralscha.e4ds.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.e4ds.entity.LoggingEvent;
import ch.ralscha.e4ds.repository.LoggingEventRepository;
import ch.ralscha.e4ds.util.Util;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@Service
public class LoggingEventService {

	static final ImmutableMap<String, String> mapGuiColumn2DbField = new ImmutableMap.Builder<String, String>()
			.put("dateTime", "timestmp")
			.put("message", "formattedMessage")
			.put("level", "levelString")
			.build();

	@Autowired
	private LoggingEventRepository loggingEventRepository;

	@ExtDirectMethod(STORE_READ)
	@Transactional(readOnly = true)
	public ExtDirectStoreResponse<LoggingEventDto> load(ExtDirectStoreReadRequest request) {

		Page<LoggingEvent> page = loggingEventRepository.findAll(Util.createPageRequest(request, mapGuiColumn2DbField));

		List<LoggingEventDto> loggingEventList = Lists.newArrayList();
		for (LoggingEvent event : page.getContent()) {
			loggingEventList.add(new LoggingEventDto(event));
		}

		return new ExtDirectStoreResponse<LoggingEventDto>((int) page.getTotalElements(), loggingEventList);
	}

}
