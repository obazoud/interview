package com.bazoud;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class MapReduceEmulator {
    File directory = null;
    Pattern pattern = null;

    public MapReduceEmulator(File directory, Pattern pattern) {
        this.directory = directory;
        this.pattern = pattern;
    }

    public long start() {
        return Arrays.stream(directory.listFiles((f, n) -> true)).parallel().map(file -> {
                    try {
                        long sum = Files.lines(file.toPath()).parallel()
                                .filter(s -> pattern.matcher(s).matches())
                                .mapToLong(s -> 1)
                                .sum();
                        return sum;
                    } catch (Exception e) {
                        return 0;
                    }
                }
        ).collect(Collectors.counting()).intValue();
    }
}
