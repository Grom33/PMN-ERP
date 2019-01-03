package ru.gromov.notification.service.sender.sms;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.notification.domain.message.Message;
import ru.gromov.notification.domain.message.Sms;

import java.math.BigDecimal;

public interface SMSSender {

	Message sendMessage(Sms message);

	Message checkMessageStatus(Sms message);

	BigDecimal checkMessageCost(Sms message);

	BigDecimal checkBalance();

}
