package ru.gromov.verification.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpiredPassportRepositoryImpl implements ExpiredPassportRepository {

    private Map<Integer, Set<Integer>> structure = new ConcurrentHashMap<>();
    private AtomicInteger serialSetCounter= new AtomicInteger(0);
    private AtomicInteger numberCounter= new AtomicInteger(0);

    @Override
    public void add(final int serial, final int number) {
        Set<Integer> serialSet = structure.get(serial);
        if (serialSet == null) {
            serialSet = new HashSet<>();
            serialSetCounter.incrementAndGet();
        }
        serialSet.add(number);
        numberCounter.incrementAndGet();
        structure.put(serial, serialSet);
    }

    @Override
    public void delete(final int serial, final int number) {
        Set<Integer> serialSet = structure.get(serial);
        serialSet.remove(number);
        if (serialSet.size() == 0) structure.put(serial, null);
    }

    @Override
    public boolean exist(final int serial, final int number) {
        final Set<Integer> serialSet = structure.get(serial);
        if (serialSet == null) return false;
        return serialSet.contains(number);
    }
    @Override
    public void getStatistics(){
        System.out.printf("Map size %s, serialsSet count %s, number counter  %s",
                structure.size(), serialSetCounter.get(), numberCounter.get());
    }

}
