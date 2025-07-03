package com.example.demo;

import com.example.demo.controller.DemoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoController.class)
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	//1) Basic functionality with common words of varying lengths
	@Test
	@DisplayName("Remove first and last characters for common word 'country'")
	void testRemoveWithCountry() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "country"))
				.andExpect(status().isOk())
				.andExpect(content().string("ountr"));
	}

	@Test
	@DisplayName("Remove first and last characters for common word 'person'")
	void testRemoveWithPerson() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "person"))
				.andExpect(status().isOk())
				.andExpect(content().string("erso"));
	}

	// 2. Edge cases with 0 to 3-character strings
	@Test
	@DisplayName("Return 400 Bad Request for empty string")
	void testEmptyString() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", ""))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Return 400 Bad Request for single character string")
	void testSingleCharacter() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "a"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Return empty string for two-character string")
	void testTwoCharacterString() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "bc"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	@DisplayName("Return middle character for three-character string")
	void testThreeCharacterString() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "xyz"))
				.andExpect(status().isOk())
				.andExpect(content().string("y"));
	}

	// 3. Strings containing numbers and special characters
	@Test
	@DisplayName("Handle string with numbers and special characters")
	void testStringWithNumbersAndSpecialCharacters() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "123%qwerty+"))
				.andExpect(status().isOk())
				.andExpect(content().string("23%qwerty"));
	}

	@Test
	@DisplayName("Handle string with only special characters")
	void testStringWithOnlySpecialCharacters() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "#$%^&"))
				.andExpect(status().isOk())
				.andExpect(content().string("$%^"));
	}

	@Test
	@DisplayName("Handle string with numbers only")
	void testStringWithOnlyNumbers() throws Exception {
		mockMvc.perform(get("/remove").param("inputStr", "123456"))
				.andExpect(status().isOk())
				.andExpect(content().string("2345"));
	}
}
