package ru.gromov.notification.util.exception;
/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class UserNotFoundException extends MessageServiceException {
	public UserNotFoundException(String message) {
		super(message);
	}
}
