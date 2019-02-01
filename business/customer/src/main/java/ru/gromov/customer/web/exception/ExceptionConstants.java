package ru.gromov.customer.web.exception;

import java.net.URI;

public interface ExceptionConstants {
    String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    String ERR_VALIDATION = "error.validation";
    String PROBLEM_BASE_URL = "/problem";
    URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
    URI ENTITY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/entity-not-found");
    URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
    URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");
}
