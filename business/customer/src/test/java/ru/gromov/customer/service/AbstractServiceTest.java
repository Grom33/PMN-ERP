package ru.gromov.customer.service;/*
 *   Created by Gromov Vitaly (Grom33), 2019
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbstractServiceTest {
	protected final Address ADDRESS_NEW = new Address();
	protected final Passport PASSPORT_NEW = new Passport();
	protected final Phone PHONE_NEW = new Phone();
	protected final Customer CUSTOMER_NEW = new Customer();

	protected final Address ADDRESS_IN_BASE_ID_1 = new Address();
	protected final Address ADDRESS_IN_BASE_ID_2 = new Address();
	protected final Passport PASSPORT_IN_BASE_ID_1 = new Passport();
	protected final Phone PHONE_IN_BASE_ID_1 = new Phone();
	protected final Phone PHONE_IN_BASE_ID_2 = new Phone();
	protected final Customer CUSTOMER_IN_BASE_ID_1 = new Customer();

	protected final List<Address> ADDRESS_LIST_OF_CUSTOMER_1 = new ArrayList<>();
	protected final List<Phone> PHONE_LIST_OF_CUSTOMER_1 = new ArrayList<>();

	{
		ADDRESS_NEW.setCustomerId(1L);
		ADDRESS_NEW.setAddressType(AddressType.REGISTRATION);
		ADDRESS_NEW.setZipCode("163000");
		ADDRESS_NEW.setRegion("Ленинградская область");
		ADDRESS_NEW.setCity("Санкт-Петербург");
		ADDRESS_NEW.setStreet("Мебельная");
		ADDRESS_NEW.setHouse("123/1");
		ADDRESS_NEW.setFlat("444");

		PASSPORT_NEW.setCustomerId(5);
		PASSPORT_NEW.setSerial("12 09");
		PASSPORT_NEW.setNumber("567098");
		PASSPORT_NEW.setDate(LocalDate.of(2018, Month.DECEMBER, 12));
		PASSPORT_NEW.setPassportOffice("ОВД Цветочного округа");
		PASSPORT_NEW.setOfficeCode("123-001");
		PASSPORT_NEW.setExpirationDate(LocalDate.of(2025, Month.DECEMBER, 12));

		PHONE_NEW.setCustomerId(5);
		PHONE_NEW.setPhoneType(PhoneType.MOBILE);
		PHONE_NEW.setNumber("202-888-8899");

		CUSTOMER_NEW.setCustomerUuid(UUID.randomUUID());
		CUSTOMER_NEW.setComplianceStatus(ComplianceStatus.GRANTED);
		CUSTOMER_NEW.setName("Джон");
		CUSTOMER_NEW.setMiddleName("Иванович");
		CUSTOMER_NEW.setSurname("Сталоне");
		CUSTOMER_NEW.setGender(Gender.MALE);
		CUSTOMER_NEW.setBirthPlace("Воркута");
		CUSTOMER_NEW.setBirthday(LocalDate.of(1985, Month.AUGUST, 30));
		CUSTOMER_NEW.setINN("780987112765");
		CUSTOMER_NEW.setSNILS("235-456-432-96");

		ADDRESS_IN_BASE_ID_1.setId(1L);
		ADDRESS_IN_BASE_ID_1.setCustomerId(1L);
		ADDRESS_IN_BASE_ID_1.setAddressType(AddressType.RESIDENCE);
		ADDRESS_IN_BASE_ID_1.setZipCode("123456");
		ADDRESS_IN_BASE_ID_1.setRegion("Ленинаградская область");
		ADDRESS_IN_BASE_ID_1.setCity("Санкт-Петербург");
		ADDRESS_IN_BASE_ID_1.setStreet("Оптиков");
		ADDRESS_IN_BASE_ID_1.setHouse("123");
		ADDRESS_IN_BASE_ID_1.setFlat("110");

		ADDRESS_IN_BASE_ID_2.setId(2L);
		ADDRESS_IN_BASE_ID_2.setCustomerId(1L);
		ADDRESS_IN_BASE_ID_2.setAddressType(AddressType.REGISTRATION);
		ADDRESS_IN_BASE_ID_2.setZipCode("123000");
		ADDRESS_IN_BASE_ID_2.setRegion("Ленинаградская область");
		ADDRESS_IN_BASE_ID_2.setCity("Санкт-Петербург");
		ADDRESS_IN_BASE_ID_2.setStreet("Оптиков");
		ADDRESS_IN_BASE_ID_2.setHouse("123");
		ADDRESS_IN_BASE_ID_2.setFlat("110");

		PASSPORT_IN_BASE_ID_1.setId(1L);
		PASSPORT_IN_BASE_ID_1.setCustomerId(1L);
		PASSPORT_IN_BASE_ID_1.setSerial("11 02");
		PASSPORT_IN_BASE_ID_1.setNumber("789654");
		PASSPORT_IN_BASE_ID_1.setDate(LocalDate.of(2006, Month.NOVEMBER, 23));
		PASSPORT_IN_BASE_ID_1.setPassportOffice("ОВД ПРИМОРСКОГО РАЙОНА");
		PASSPORT_IN_BASE_ID_1.setOfficeCode("770-001");
		PASSPORT_IN_BASE_ID_1.setExpirationDate(LocalDate.of(2226, Month.NOVEMBER, 23));

		PHONE_IN_BASE_ID_1.setId(1L);
		PHONE_IN_BASE_ID_1.setCustomerId(1L);
		PHONE_IN_BASE_ID_1.setPhoneType(PhoneType.MOBILE);
		PHONE_IN_BASE_ID_1.setNumber("903-165-8429");

		PHONE_IN_BASE_ID_2.setId(2L);
		PHONE_IN_BASE_ID_2.setCustomerId(1L);
		PHONE_IN_BASE_ID_2.setPhoneType(PhoneType.HOME);
		PHONE_IN_BASE_ID_2.setNumber("911-765-2899");

		CUSTOMER_IN_BASE_ID_1.setId(1L);
		CUSTOMER_IN_BASE_ID_1.setName("ИВАН");
		CUSTOMER_IN_BASE_ID_1.setMiddleName("ИВАНОВИЧ");
		CUSTOMER_IN_BASE_ID_1.setSurname("ИВАНОВ");
		CUSTOMER_IN_BASE_ID_1.setGender(Gender.MALE);
		CUSTOMER_IN_BASE_ID_1.setBirthPlace("САНКТ-ПЕТЕРБУРГ");
		CUSTOMER_IN_BASE_ID_1.setBirthday(LocalDate.of(1982, Month.MARCH, 2));
		CUSTOMER_IN_BASE_ID_1.setEmail("ivan@mail.ru");
		CUSTOMER_IN_BASE_ID_1.setINN("123456789");
		CUSTOMER_IN_BASE_ID_1.setSNILS("123-123-345-78");
		CUSTOMER_IN_BASE_ID_1.setComplianceStatus(ComplianceStatus.GRANTED);

		ADDRESS_LIST_OF_CUSTOMER_1.add(ADDRESS_IN_BASE_ID_1);
		ADDRESS_LIST_OF_CUSTOMER_1.add(ADDRESS_IN_BASE_ID_2);

		PHONE_LIST_OF_CUSTOMER_1.add(PHONE_IN_BASE_ID_2);
		PHONE_LIST_OF_CUSTOMER_1.add(PHONE_IN_BASE_ID_1);
	}
}
