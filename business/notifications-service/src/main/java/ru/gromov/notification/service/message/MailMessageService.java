package ru.gromov.notification.service.message;

import ru.gromov.notification.domain.message.MailMessage;

import java.util.List;
import java.util.UUID;

public interface MailMessageService {
    long count();

    MailMessage send(MailMessage mailMessage);

    MailMessage save(MailMessage mailMessage);

    MailMessage getById(Long id);

    MailMessage getByUuiD(UUID uuid);

    List<MailMessage> getAll();

    MailMessage archive(UUID uuid);

    void delete(Long id);
}