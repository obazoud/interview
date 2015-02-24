package com.bazoud;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.io.File;
import java.util.regex.Pattern;

public class MapReduceEmulatorTest {

    @Test
    public void test_map_reduce() {
        MapReduceEmulator emulator = new MapReduceEmulator(getDirectory(), Pattern.compile("^z.*$"));
        assertThat(emulator.start(), is(5));
    }

    private File getDirectory() {
        return new File(Thread.currentThread().getContextClassLoader().getResource("logs").getFile());
    }
}
