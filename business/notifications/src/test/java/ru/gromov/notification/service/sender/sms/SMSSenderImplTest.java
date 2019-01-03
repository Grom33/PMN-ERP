package ru.gromov.notification.service.sender.sms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gromov.notification.domain.message.Sms;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class SMSSenderImplTest {
	@Autowired
	SMSSender sender;

	@Test
	public void send() throws InterruptedException {
		Sms sms = new Sms();
		sms.setPhoneNumber("79052295372");
		sms.setMessage("You are sexy!");
		System.out.println(sender.sendMessage(sms));
		Thread.sleep(5000);
		System.out.println(sender.checkMessageStatus(sms));
	}

	@Test
	public void check() {
		Sms sms = new Sms();
		sms.setPhoneNumber("79062820232");
		sms.setSmsId("201850-1000012");
		System.out.println(sender.checkMessageStatus(sms));

	}

	@Test
	public void cost() {
		Sms sms = new Sms();
		sms.setPhoneNumber("79062820232");
		sms.setMessage("asdasdasdasdasd");
		System.out.println(sender.checkMessageCost(sms));
	}

	@Test
	public void balance() {
		System.out.println(sender.checkBalance());

	}
}