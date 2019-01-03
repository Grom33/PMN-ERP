

package ru.gromov.notification.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gromov.notification.domain.message.MailMessage;
import ru.gromov.notification.domain.message.MessageStatus;
import ru.gromov.notification.repository.MailRepository;
import ru.gromov.notification.util.exception.NotFoundException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MailMessageServiceImpl implements MailMessageService {
	@Autowired
	private final PollableChannel toSend;

	@Autowired
	private MailRepository mailRepository;

	//@Secured("ROLE_ADMIN")
	@Transactional(readOnly = true)
	@Override
	public long count() {
		return mailRepository.count();
	}

	@Override
	public MailMessage send(MailMessage mailMessage) {
		MailMessage returnedMessage = save(mailMessage);
		toSend.send(MessageBuilder.withPayload(returnedMessage).build());
		return returnedMessage;
	}

	@Transactional
	@Override
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public MailMessage save(MailMessage mailMessage) {
		return mailRepository.save(mailMessage);
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@Transactional(readOnly = true)
	@Override
	public MailMessage getById(Long id) {
		return mailRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(
						String.format("MailMessage with id: %s not found!", id)));
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@Transactional(readOnly = true)
	@Override
	public MailMessage getByUuiD(UUID uuid) {
		return mailRepository.findByUuid(uuid)
				.orElseThrow(() -> new NotFoundException(
						String.format("MailMessage with uuid: %s not found!", uuid)));
	}

	//@Secured({"ROLE_ADMIN"})
	@Transactional(readOnly = true)
	@Override
	public List<MailMessage> getAll() {
		return mailRepository.findAll();
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@Transactional
	@Override
	public MailMessage archive(UUID uuid) {
		MailMessage message = mailRepository.findByUuid(uuid)
				.orElseThrow(() -> new NotFoundException(
						String.format("MailMessage with uuid: %s not found!", uuid)));
		message.setStatus(MessageStatus.DELETED);
		return mailRepository.save(message);
	}

	//@Secured({"ROLE_ADMIN"})
	@Override
	@Transactional
	public void delete(Long id) {
		mailRepository.deleteById(id);
	}
}
