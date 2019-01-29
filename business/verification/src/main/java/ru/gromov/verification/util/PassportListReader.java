package ru.gromov.verification.util;

import ru.gromov.verification.repository.ExpiredPassportRepository;

import java.io.*;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Stream;

public class PassportListReader {
    public static void readList(ExpiredPassportRepository repository, String fileName, Set<Integer> exist) {
        File file = new File(fileName);
        try (Stream linesStream = Files.lines(file.toPath())) {
            long start1 = System.nanoTime();
            linesStream.forEach(line -> {
                String[] pair = ((String) line).split(",");
                int serial, number;
                try {
                    serial = Integer.valueOf(pair[0].trim());
                    number = Integer.valueOf(pair[1].trim());

                    repository.add(serial, number);
                } catch (Exception e) {
                }
            });
            long time1 = System.nanoTime() - start1;
            System.out.printf("Took %.3f seconds ", time1 / 1e9);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


