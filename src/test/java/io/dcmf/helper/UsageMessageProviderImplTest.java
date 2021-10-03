package io.dcmf.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsageMessageProviderImplTest {

    @Test
    void getMessage() {
        UsageMessageProvider provider = new UsageMessageProviderImpl();
        String message = provider.getMessage();
        assertNotNull(message);
        assertTrue(message.startsWith("Usage:"));
        assertTrue(message.contains("java -jar"));
        assertTrue(message.contains("AudioSplitter"));
    }
}