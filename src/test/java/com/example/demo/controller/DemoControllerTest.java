package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Skeleton template for a controller test using MockMvc.
 *
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 * @CsvSource and @MethodSource for your test data.
 *
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 *                 .andExpect(status().whateverStatusCodeYouExpect())
 *                 .andExpect(content().string("string-you-expect-in-response")).
 *                 .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @DisplayName("Remove first and last characters for various valid inputs")
    @CsvSource({
            "country,ountr",
            "person,erso",
            "xyz,y",
            "123%qwerty+,23%qwerty",
            "#$%^&,$%^",
            "123456,2345"
    })
    void testRemoveFirstAndLastCharacters(String input, String expectedOutput) throws Exception {
        mockMvc.perform(get("/remove").param("inputStr", input))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedOutput));
    }

    @ParameterizedTest
    @DisplayName("Return empty string for two-character strings")
    @ValueSource(strings = {"ab", "bc", "xy", "12", "@#"})
    void testTwoCharacterStrings(String input) throws Exception {
        mockMvc.perform(get("/remove").param("inputStr", input))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @ParameterizedTest
    @DisplayName("Return 400 Bad Request for invalid short strings")
    @ValueSource(strings = {"", "a", "1", "@"})
    void testInvalidShortStrings(String input) throws Exception {
        mockMvc.perform(get("/remove").param("inputStr", input))
                .andExpect(status().isBadRequest());
    }
}
