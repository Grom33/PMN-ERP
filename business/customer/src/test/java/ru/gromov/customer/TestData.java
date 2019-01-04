package ru.gromov.customer;

import ru.gromov.common.domain.compliance.ComplianceStatus;
import ru.gromov.customer.domain.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 *   Created by Gromov Vitaly, 2019   e-mail: mr.gromov.vitaly@gmail.com
 */

public class TestData {


	public static final Address ADDRESS_NEW = new Address();
	public static final Passport PASSPORT_NEW = new Passport();
	public static final Phone PHONE_NEW = new Phone();
	public static final Customer CUSTOMER_NEW = new Customer();
	public static final BankAccount BANK_ACCOUNT_NEW = new BankAccount();


	public static final int CUSTOMER_COUNT = 5;
	public static final int ACTIVE_CUSTOMER_COUNT = 4;
	public static final int BLOCKED_CUSTOMER_COUNT = 1;
	public static final Address ADDRESS_IN_BASE_ID_1 = new Address();
	public static final Address ADDRESS_IN_BASE_ID_2 = new Address();
	public static final Address ADDRESS_IN_BASE_ID_3_NOT_ACTIVE = new Address();

	public static final Passport PASSPORT_IN_BASE_ID_1 = new Passport();
	public static final Passport PASSPORT_IN_BASE_ID_6_NOT_ACTIVE = new Passport();

	public static final Phone PHONE_IN_BASE_ID_1 = new Phone();
	public static final Phone PHONE_IN_BASE_ID_2 = new Phone();

	public static final BankAccount BANK_ACCOUNT_IN_BASE_ID_1 = new BankAccount();
	public static final BankAccount BANK_ACCOUNT_IN_BASE_ID_2 = new BankAccount();

	public static final Customer CUSTOMER_IN_BASE_ID_1 = new Customer();

	public static final List<Address> ADDRESS_LIST_OF_CUSTOMER_1 = new ArrayList<>();
	public static final List<Address> ADDRESS_LIST_OF_CUSTOMER_1_ALL = new ArrayList<>();
	public static final List<Passport> PASSPORT_LIST_OF_CUSTOMER_1 = new ArrayList<>();
	public static final List<Passport> PASSPORT_LIST_OF_CUSTOMER_1_ALL = new ArrayList<>();
	public static final List<Phone> PHONE_LIST_OF_CUSTOMER_1 = new ArrayList<>();
	public static final List<BankAccount> BANK_ACCOUNT_LIST_OF_CUSTOMER_1 = new ArrayList<>();
	public static final List<BankAccount> BANK_ACCOUNT_LIST_OF_CUSTOMER_1_ALL = new ArrayList<>();

	static {
		ADDRESS_NEW.setCustomerId(1L);
		ADDRESS_NEW.setActive(true);
		ADDRESS_NEW.setTrash(false);
		ADDRESS_NEW.setAddressType(AddressType.REGISTRATION);
		ADDRESS_NEW.setZipCode("163000");
		ADDRESS_NEW.setRegion("Ленинградская область");
		ADDRESS_NEW.setCity("Санкт-Петербург");
		ADDRESS_NEW.setStreet("Мебельная");
		ADDRESS_NEW.setHouse("123/1");
		ADDRESS_NEW.setFlat("444");

		PASSPORT_NEW.setCustomerId(5);
		PASSPORT_NEW.setActive(true);
		PASSPORT_NEW.setTrash(false);
		PASSPORT_NEW.setSerial("12 09");
		PASSPORT_NEW.setNumber("567098");
		PASSPORT_NEW.setDate(LocalDate.of(2018, Month.DECEMBER, 12));
		PASSPORT_NEW.setPassportOffice("ОВД Цветочного округа");
		PASSPORT_NEW.setOfficeCode("123-001");
		PASSPORT_NEW.setExpirationDate(LocalDate.of(2025, Month.DECEMBER, 12));

		PHONE_NEW.setCustomerId(5);
		PHONE_NEW.setActive(true);
		PHONE_NEW.setTrash(false);
		PHONE_NEW.setPhoneType(PhoneType.MOBILE);
		PHONE_NEW.setNumber("202-888-8899");

		CUSTOMER_NEW.setCustomerUuid(UUID.randomUUID());
		CUSTOMER_NEW.setActive(true);
		CUSTOMER_NEW.setTrash(false);
		CUSTOMER_NEW.setComplianceStatus(ComplianceStatus.GRANTED);
		CUSTOMER_NEW.setName("Джон");
		CUSTOMER_NEW.setMiddleName("Иванович");
		CUSTOMER_NEW.setSurname("Сталоне");
		CUSTOMER_NEW.setGender(Gender.MALE);
		CUSTOMER_NEW.setBirthPlace("Воркута");
		CUSTOMER_NEW.setBirthday(LocalDate.of(1985, Month.AUGUST, 30));
		CUSTOMER_NEW.setINN("780987112765");
		CUSTOMER_NEW.setSNILS("235-456-432-96");


		BANK_ACCOUNT_NEW.setActive(true);
		BANK_ACCOUNT_NEW.setTrash(false);
		BANK_ACCOUNT_NEW.setAccountNumber("12345678901234567890");
		BANK_ACCOUNT_NEW.setBankBIK("1234567890");
		BANK_ACCOUNT_NEW.setBankINN("123456098");
		BANK_ACCOUNT_NEW.setBankName("ВТБ");
		BANK_ACCOUNT_NEW.setBeneficiaryName("ПЕТР ВАСИЛЬЕВИЧ СМИРНОВ");
		BANK_ACCOUNT_NEW.setCorrespondentAccount("12345678901234567890");
		BANK_ACCOUNT_NEW.setCustomerId(1L);

		BANK_ACCOUNT_IN_BASE_ID_1.setId(1L);
		BANK_ACCOUNT_IN_BASE_ID_1.setActive(true);
		BANK_ACCOUNT_IN_BASE_ID_1.setTrash(false);
		BANK_ACCOUNT_IN_BASE_ID_1.setCustomerId(1L);
		BANK_ACCOUNT_IN_BASE_ID_1.setBeneficiaryName("ИВАН ИВАНОВИЧ ИВАНОВ");
		BANK_ACCOUNT_IN_BASE_ID_1.setAccountNumber("12345678901234567890");
		BANK_ACCOUNT_IN_BASE_ID_1.setBankName("ВТБ");
		BANK_ACCOUNT_IN_BASE_ID_1.setBankINN("1234567890");
		BANK_ACCOUNT_IN_BASE_ID_1.setBankBIK("123456098");
		BANK_ACCOUNT_IN_BASE_ID_1.setCorrespondentAccount("12345678901234567890");

		BANK_ACCOUNT_IN_BASE_ID_2.setId(2L);
		BANK_ACCOUNT_IN_BASE_ID_2.setActive(false);
		BANK_ACCOUNT_IN_BASE_ID_2.setTrash(false);
		BANK_ACCOUNT_IN_BASE_ID_2.setCustomerId(1L);
		BANK_ACCOUNT_IN_BASE_ID_2.setBeneficiaryName("ИВАН ИВАНОВИЧ ИВАНОВ");
		BANK_ACCOUNT_IN_BASE_ID_2.setAccountNumber("12345678901234567890");
		BANK_ACCOUNT_IN_BASE_ID_2.setBankName("ВТБ");
		BANK_ACCOUNT_IN_BASE_ID_2.setBankINN("1234567890");
		BANK_ACCOUNT_IN_BASE_ID_2.setBankBIK("123456098");
		BANK_ACCOUNT_IN_BASE_ID_2.setCorrespondentAccount("12345678901234567890");

		ADDRESS_IN_BASE_ID_1.setId(1L);
		ADDRESS_IN_BASE_ID_1.setActive(true);
		ADDRESS_IN_BASE_ID_1.setTrash(false);
		ADDRESS_IN_BASE_ID_1.setCustomerId(1L);
		ADDRESS_IN_BASE_ID_1.setAddressType(AddressType.RESIDENCE);
		ADDRESS_IN_BASE_ID_1.setZipCode("123456");
		ADDRESS_IN_BASE_ID_1.setRegion("Ленинаградская область");
		ADDRESS_IN_BASE_ID_1.setCity("Санкт-Петербург");
		ADDRESS_IN_BASE_ID_1.setStreet("Оптиков");
		ADDRESS_IN_BASE_ID_1.setHouse("123");
		ADDRESS_IN_BASE_ID_1.setFlat("110");

		ADDRESS_IN_BASE_ID_2.setId(2L);
		ADDRESS_IN_BASE_ID_2.setActive(true);
		ADDRESS_IN_BASE_ID_2.setTrash(false);
		ADDRESS_IN_BASE_ID_2.setCustomerId(1L);
		ADDRESS_IN_BASE_ID_2.setAddressType(AddressType.REGISTRATION);
		ADDRESS_IN_BASE_ID_2.setZipCode("123000");
		ADDRESS_IN_BASE_ID_2.setRegion("Ленинаградская область");
		ADDRESS_IN_BASE_ID_2.setCity("Санкт-Петербург");
		ADDRESS_IN_BASE_ID_2.setStreet("Оптиков");
		ADDRESS_IN_BASE_ID_2.setHouse("123");
		ADDRESS_IN_BASE_ID_2.setFlat("110");

		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setId(9L);
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setActive(false);
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setTrash(false);
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setCustomerId(1L);
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setAddressType(AddressType.REGISTRATION);
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setZipCode("110000");
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setRegion("Московская область");
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setCity("Московка");
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setStreet("Цветная");
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setHouse("13");
		ADDRESS_IN_BASE_ID_3_NOT_ACTIVE.setFlat("10");

		PASSPORT_IN_BASE_ID_1.setId(1L);
		PASSPORT_IN_BASE_ID_1.setActive(true);
		PASSPORT_IN_BASE_ID_1.setTrash(false);
		PASSPORT_IN_BASE_ID_1.setCustomerId(1L);
		PASSPORT_IN_BASE_ID_1.setSerial("11 02");
		PASSPORT_IN_BASE_ID_1.setNumber("789654");
		PASSPORT_IN_BASE_ID_1.setDate(LocalDate.of(2006, Month.NOVEMBER, 23));
		PASSPORT_IN_BASE_ID_1.setPassportOffice("ОВД ПРИМОРСКОГО РАЙОНА");
		PASSPORT_IN_BASE_ID_1.setOfficeCode("770-001");
		PASSPORT_IN_BASE_ID_1.setExpirationDate(LocalDate.of(2226, Month.NOVEMBER, 23));

		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setId(6L);
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setActive(false);
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setTrash(false);
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setCustomerId(1L);
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setSerial("11 11");
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setNumber("999999");
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setDate(LocalDate.of(2001, Month.OCTOBER, 23));
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setPassportOffice("ОВД РАЙОНА");
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setOfficeCode("770-101");
		PASSPORT_IN_BASE_ID_6_NOT_ACTIVE.setExpirationDate(LocalDate.of(2026, Month.NOVEMBER, 23));

		PHONE_IN_BASE_ID_1.setId(1L);
		PHONE_IN_BASE_ID_1.setActive(true);
		PHONE_IN_BASE_ID_1.setTrash(false);
		PHONE_IN_BASE_ID_1.setCustomerId(1L);
		PHONE_IN_BASE_ID_1.setPhoneType(PhoneType.MOBILE);
		PHONE_IN_BASE_ID_1.setNumber("903-165-8429");

		PHONE_IN_BASE_ID_2.setId(2L);
		PHONE_IN_BASE_ID_2.setActive(true);
		PHONE_IN_BASE_ID_2.setTrash(false);
		PHONE_IN_BASE_ID_2.setCustomerId(1L);
		PHONE_IN_BASE_ID_2.setPhoneType(PhoneType.HOME);
		PHONE_IN_BASE_ID_2.setNumber("911-765-2899");

		CUSTOMER_IN_BASE_ID_1.setId(1L);
		CUSTOMER_IN_BASE_ID_1.setActive(true);
		CUSTOMER_IN_BASE_ID_1.setTrash(false);
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

		ADDRESS_LIST_OF_CUSTOMER_1_ALL.add(ADDRESS_IN_BASE_ID_1);
		ADDRESS_LIST_OF_CUSTOMER_1_ALL.add(ADDRESS_IN_BASE_ID_2);
		ADDRESS_LIST_OF_CUSTOMER_1_ALL.add(ADDRESS_IN_BASE_ID_3_NOT_ACTIVE);

		PASSPORT_LIST_OF_CUSTOMER_1.add(PASSPORT_IN_BASE_ID_1);

		PASSPORT_LIST_OF_CUSTOMER_1_ALL.add(PASSPORT_IN_BASE_ID_1);
		PASSPORT_LIST_OF_CUSTOMER_1_ALL.add(PASSPORT_IN_BASE_ID_6_NOT_ACTIVE);

		PHONE_LIST_OF_CUSTOMER_1.add(PHONE_IN_BASE_ID_1);
		PHONE_LIST_OF_CUSTOMER_1.add(PHONE_IN_BASE_ID_2);

		BANK_ACCOUNT_LIST_OF_CUSTOMER_1.add(BANK_ACCOUNT_IN_BASE_ID_1);

		BANK_ACCOUNT_LIST_OF_CUSTOMER_1_ALL.add(BANK_ACCOUNT_IN_BASE_ID_1);
		BANK_ACCOUNT_LIST_OF_CUSTOMER_1_ALL.add(BANK_ACCOUNT_IN_BASE_ID_2);

	}

	private TestData() {
	}
}
