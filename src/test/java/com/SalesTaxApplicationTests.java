package com;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.service.SalesTaxService;

@AutoConfigureMockMvc
@SpringBootTest
class SalesTaxApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SalesTaxService salesTaxService;

	@Test
	public void testGenerateReport() throws Exception {
		String jsonRequest = "[\n" +
                "  {\n" +
                "    \"quantity\": 1,\n" +
                "    \"name\": \"book\",\n" +
                "    \"price\": 12.49,\n" +
                "    \"isImported\": false,\n" +
                "    \"category\": \"BOOKS\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"quantity\": 1,\n" +
                "    \"name\": \"music CD\",\n" +
                "    \"price\": 14.99,\n" +
                "    \"isImported\": false,\n" +
                "    \"category\": \"DIGITAL\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"quantity\": 1,\n" +
                "    \"name\": \"chocolate bar\",\n" +
                "    \"price\": 0.85,\n" +
                "    \"isImported\": false,\n" +
                "    \"category\": \"FOOD\"\n" +
                "  }\n" +
                "]";

		mockMvc.perform(post("/api/tax/generateReport").contentType("application/json").content(jsonRequest))
				.andExpect(status().isOk()) 
				.andExpect(jsonPath("$.total").value(29.85)) 
				.andExpect(jsonPath("$.totalTax").value(1.50)) 
				.andExpect(jsonPath("$.items[0]").value("1 book of catagory BOOKS at : 12.49")) 																				
				.andExpect(jsonPath("$.items[1]").value("1 music CD of catagory DIGITAL at : 16.49")) 																						
				.andExpect(jsonPath("$.items[2]").value("1 chocolate bar of catagory FOOD at : 0.85")); 
																										
	}

	@Test
	void contextLoads() {

	}
}