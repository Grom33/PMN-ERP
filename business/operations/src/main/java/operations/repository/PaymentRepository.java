package operations.repository;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import operations.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
