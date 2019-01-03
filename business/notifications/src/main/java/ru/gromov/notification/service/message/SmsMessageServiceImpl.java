package ru.gromov.notification.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.notification.domain.message.MessageStatus;
import ru.gromov.notification.domain.message.Sms;
import ru.gromov.notification.repository.SMSRepository;
import ru.gromov.notification.service.sender.sms.SMSSender;
import ru.gromov.notification.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SmsMessageServiceImpl implements SmsMessageService {

	@Autowired
	private SMSRepository smsRepository;

	@Autowired
	private PollableChannel toSend;

	@Autowired
	private final SMSSender sender;


	@Transactional(readOnly = true)
	@Override
	//@Secured("ROLE_ADMIN")
	public long count() {
		return smsRepository.count();
	}

	@Override
	public Sms send(Sms sms) {
		Sms returnedSms = save(sms);
		toSend.send(MessageBuilder.withPayload(returnedSms).build());
		return returnedSms;
	}


	@Transactional
	@Override
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public Sms save(Sms sms) {
		return smsRepository.save(sms);
	}

	@Transactional(readOnly = true)
	@Override
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public Sms getById(Long id) {
		return getSms(id, (i) -> smsRepository.findById(i));
	}

	@Override
	public Sms getByUuiD(UUID uuid) {
		return getSms(uuid, (id) -> smsRepository.findByUuid(id));
	}

	@Transactional(readOnly = true)
	@Override
	//@Secured({"ROLE_ADMIN"})
	public List<Sms> getAll() {
		return smsRepository.findAll();
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@Override
	@Transactional
	public Sms archive(UUID uuid) {
		Sms message = getByUuiD(uuid);
		message.setStatus(MessageStatus.DELETED);
		save(message);
		return message;
	}

	@Override
	public Sms checkStatus(UUID uuid) {
		return (Sms) sender.checkMessageStatus(getByUuiD(uuid));
	}

	//@Secured({"ROLE_ADMIN"})
	@Transactional
	@Override
	public void delete(Long id) {
		smsRepository.delete(getById(id));
	}

	private <T> Sms getSms(T id, Function<T, Optional<Sms>> getFunction) {
		return getFunction.apply(id)
				.orElseThrow(() -> new NotFoundException(
						String.format("Sms with id(%s): %s not found!",
								id.getClass().getSimpleName(), id)));
	}
}
