package ru.gromov.verification.repository;

import org.springframework.stereotype.Component;

@Component
public interface ExpiredPassportRepository {

    void add(int serial, int number);

    void delete(int serial, int number);

    boolean exist(int serial, int number);

    void getStatistics();
}
