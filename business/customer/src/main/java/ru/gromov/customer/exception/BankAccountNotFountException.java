package ru.gromov.customer.exception;/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

public class BankAccountNotFountException extends NotFoundException {
	public BankAccountNotFountException(String message) {
		super(message);
	}
}
