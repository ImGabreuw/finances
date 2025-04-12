package br.com.gabreuw.finances.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestHelper {

    public static String getTestResourceAbsolutePath(Class<?> testClass, String filename) throws URISyntaxException {
        URL resource = testClass.getClassLoader().getResource(filename);

        assertThat(resource)
                .withFailMessage("Arquivo CSV (%s) não encontrado nos recursos de teste".formatted(filename))
                .isNotNull();

        Path resourcePath = Paths.get(resource.toURI());
        return resourcePath.toString();
    }

}
