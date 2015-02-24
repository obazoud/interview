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
                        return Files.lines(file.toPath()).parallel()
                                .filter(s -> pattern.matcher(s).matches())
                                .mapToLong(s -> 1L)
                                .sum();
                    } catch (Exception e) {
                        return 0;
                    }
                }
        ).collect(Collectors.summingLong(p -> p.longValue()));
    }
}
