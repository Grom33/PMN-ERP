package ru.gromov.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gromov.notification.domain.message.MailMessage;

import java.util.Optional;
import java.util.UUID;

public interface MailRepository extends JpaRepository<MailMessage, Long> {

	Optional<MailMessage> findByUuid(UUID uuid);
}
