package br.com.gabreuw.finances.shared.logging;

import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class LogContext {

    public static final String CORRELATION_ID = "correlation.id";
    public static final String COMPONENT = "service.name";
    public static final String USER_ID = "user.id";

    public static void putCorrelationId() {
        String correlationId = UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID, correlationId);
    }

    public static void putCorrelationId(String correlationId) {
        MDC.put(CORRELATION_ID, correlationId);
    }

    public static void putComponent(String component) {
        MDC.put(COMPONENT, component);
    }

    public static void putUserId(String userId) {
        MDC.put(USER_ID, userId);
    }

    public static void put(String key, String value) {
        if (value != null) {
            MDC.put(key, value);
        }
    }

    public static void remove(String key) {
        MDC.remove(key);
    }

    public static void clear() {
        MDC.clear();
    }

    public static <T> T withContext(Map<String, String> context, Supplier<T> operation) {
        Map<String, String> oldContext = MDC.getCopyOfContextMap();
        try {
            context.forEach(MDC::put);
            return operation.get();
        } finally {
            MDC.clear();
            if (oldContext != null) {
                oldContext.forEach(MDC::put);
            }
        }
    }
}