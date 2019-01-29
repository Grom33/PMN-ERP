package ru.gromov.verification;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gromov.verification.repository.ExpiredPassportRepository;
import ru.gromov.verification.repository.ExpiredPassportRepositoryImpl;
import ru.gromov.verification.util.PassportListReader;

import java.util.HashSet;

@SpringBootApplication
public class Verification {
	public static void main(String[] args) {
		ExpiredPassportRepository repository = new ExpiredPassportRepositoryImpl();
		PassportListReader.readList(repository,
				"/home/grom/projects/PMNERP/rfmbase/list_of_expired_passports.csv",new HashSet<>());
		repository.getStatistics();
		System.out.printf("7508 375436 is %s", repository.exist(7508, 375436));
		//SpringApplication.run(Verification.class);
	}
}
