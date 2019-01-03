package ru.gromov.customer.exception;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
