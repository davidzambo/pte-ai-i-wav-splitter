package io.dcmf.audiosplitter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class AbstractSystemConsoleTest {
    protected ByteArrayOutputStream outputStream;
    protected ByteArrayOutputStream errorStream;
    private final PrintStream out = System.out;
    private final PrintStream err = System.err;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        errorStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }
}
