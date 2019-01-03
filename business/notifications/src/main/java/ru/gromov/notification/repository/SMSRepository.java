package ru.gromov.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gromov.notification.domain.message.Sms;

import java.util.Optional;
import java.util.UUID;

public interface SMSRepository extends JpaRepository<Sms, Long> {

	Optional<Sms> findByUuid(UUID uuid);

}
