package br.com.gabreuw.finances.shared.helper;

import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ReflectionHelper {

    public static boolean isFieldTypeEqualsTo(Field field, Class<?> type) {
        return field.getType().isAssignableFrom(type);
    }

    public static List<Field> filterFieldsByType(Object object, Class<?> filter) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> isFieldTypeEqualsTo(field, filter))
                .toList();
    }

}
