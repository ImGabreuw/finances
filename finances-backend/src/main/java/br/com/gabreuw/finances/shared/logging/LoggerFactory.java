package br.com.gabreuw.finances.shared.logging;

public class LoggerFactory {

    public static ApplicationLogger getLogger(Class<?> clazz) {
        return new EcsLogger(clazz);
    }

}
