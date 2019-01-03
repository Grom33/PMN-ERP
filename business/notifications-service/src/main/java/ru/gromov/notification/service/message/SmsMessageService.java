package ru.gromov.notification.service.message;

import ru.gromov.notification.domain.message.Sms;

import java.util.List;
import java.util.UUID;

public interface SmsMessageService {

	long count();

	Sms send(Sms sms);

	Sms save(Sms sms);

	Sms getById(Long id);

	Sms getByUuiD(UUID uuid);

	List<Sms> getAll();

	Sms archive(UUID uuid);

	Sms checkStatus(UUID uuid);

	void delete(Long id);
}
