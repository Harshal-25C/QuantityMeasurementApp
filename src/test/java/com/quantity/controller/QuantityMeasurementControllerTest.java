
package com.quantity.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;
import com.quantity.exception.GlobalExceptionHandler;
import com.quantity.security.JwtAuthFilter;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.service.JwtService;

@WebMvcTest(QuantityMeasurementController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
public class QuantityMeasurementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IQuantityMeasurementService service;
	
	@MockBean
	private JwtService jwtService;

	@MockBean
	private JwtAuthFilter jwtAuthFilter;

	// ===== Helper methods =====

	private QuantityInputDTO buildInput(double v1, String u1, String t1, double v2, String u2, String t2) {
		QuantityDTO q1 = new QuantityDTO(v1, u1, t1);
		QuantityDTO q2 = new QuantityDTO(v2, u2, t2);
		return new QuantityInputDTO(q1, q2, null);
	}

	private QuantityMeasurementDTO buildResult(String operation, double resultValue, String resultUnit,
			String resultMeasurementType) {
		QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
		dto.setOperation(operation);
		dto.setResultValue(resultValue);
		dto.setResultUnit(resultUnit);
		dto.setResultMeasurementType(resultMeasurementType);
		dto.setError(false);
		return dto;
	}

	// ================= COMPARE TESTS =================

	@Test
	@WithMockUser
	public void testCompareQuantities_Success() throws Exception {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		QuantityMeasurementDTO mockResult = buildResult("compare", 0.0, null, null);
		mockResult.setResultString("true");

		Mockito.when(service.compare(Mockito.any(QuantityInputDTO.class))).thenReturn(mockResult);

		mockMvc.perform(post("/api/v1/quantities/compare").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk())
				.andExpect(jsonPath("$.operation").value("compare")).andExpect(jsonPath("$.resultString").value("true"))
				.andExpect(jsonPath("$.error").value(false));
	}

	// ================= CONVERT TESTS =================

	@Test
	@WithMockUser
	public void testConvertQuantity_Success() throws Exception {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 0.0, "INCHES", "LengthUnit");

		QuantityMeasurementDTO mockResult = buildResult("convert", 12.0, null, "LengthUnit");

		Mockito.when(service.convert(Mockito.any(QuantityInputDTO.class))).thenReturn(mockResult);

		mockMvc.perform(post("/api/v1/quantities/convert").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk())
				.andExpect(jsonPath("$.operation").value("convert")).andExpect(jsonPath("$.resultValue").value(12.0))
				.andExpect(jsonPath("$.error").value(false));
	}

	// ================= ADD TESTS =================

	@Test
	@WithMockUser
	public void testAddQuantities_Success() throws Exception {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		QuantityMeasurementDTO mockResult = buildResult("add", 2.0, "FEET", "LengthUnit");

		Mockito.when(service.add(Mockito.any(QuantityInputDTO.class))).thenReturn(mockResult);

		mockMvc.perform(post("/api/v1/quantities/add").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk())
				.andExpect(jsonPath("$.operation").value("add")).andExpect(jsonPath("$.resultValue").value(2.0))
				.andExpect(jsonPath("$.resultUnit").value("FEET")).andExpect(jsonPath("$.error").value(false));
	}

	// ================= SUBTRACT TESTS =================

	@Test
	@WithMockUser
	public void testSubtractQuantities_Success() throws Exception {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 6.0, "INCHES", "LengthUnit");

		QuantityMeasurementDTO mockResult = buildResult("subtract", 0.5, "FEET", "LengthUnit");

		Mockito.when(service.subtract(Mockito.any(QuantityInputDTO.class))).thenReturn(mockResult);

		mockMvc.perform(post("/api/v1/quantities/subtract").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk())
				.andExpect(jsonPath("$.operation").value("subtract")).andExpect(jsonPath("$.resultValue").value(0.5))
				.andExpect(jsonPath("$.resultUnit").value("FEET")).andExpect(jsonPath("$.error").value(false));
	}

	// ================= DIVIDE TESTS =================

	@Test
	@WithMockUser
	public void testDivideQuantities_Success() throws Exception {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		QuantityMeasurementDTO mockResult = buildResult("divide", 1.0, null, "LengthUnit");

		Mockito.when(service.divide(Mockito.any(QuantityInputDTO.class))).thenReturn(mockResult);

		mockMvc.perform(post("/api/v1/quantities/divide").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk())
				.andExpect(jsonPath("$.operation").value("divide")).andExpect(jsonPath("$.resultValue").value(1.0))
				.andExpect(jsonPath("$.error").value(false));
	}

	// ================= HISTORY BY OPERATION TESTS =================

	@Test
	@WithMockUser
	public void testGetHistoryByOperation_Success() throws Exception {

		QuantityMeasurementDTO dto1 = buildResult("compare", 0.0, null, null);
		dto1.setResultString("true");

		List<QuantityMeasurementDTO> mockList = Arrays.asList(dto1);

		Mockito.when(service.getHistoryByOperation("COMPARE")).thenReturn(mockList);

		mockMvc.perform(get("/api/v1/quantities/history/operation/COMPARE").with(csrf())).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].operation").value("compare"))
				.andExpect(jsonPath("$[0].resultString").value("true"));
	}

	// ================= HISTORY BY MEASUREMENT TYPE TESTS =================

	@Test
	@WithMockUser
	public void testGetHistoryByMeasurementType_Success() throws Exception {

		QuantityMeasurementDTO dto1 = buildResult("add", 2.0, "FEET", "LengthUnit");
		dto1.setThisMeasurementType("LengthUnit");

		List<QuantityMeasurementDTO> mockList = Arrays.asList(dto1);

		Mockito.when(service.getHistoryByMeasurementType("LengthUnit")).thenReturn(mockList);

		mockMvc.perform(get("/api/v1/quantities/history/type/LengthUnit").with(csrf())).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].resultMeasurementType").value("LengthUnit"));
	}

	// ================= OPERATION COUNT TESTS =================

	@Test
	@WithMockUser
	public void testGetOperationCount_Success() throws Exception {

		Mockito.when(service.getOperationCount("COMPARE")).thenReturn(1L);

		mockMvc.perform(get("/api/v1/quantities/count/COMPARE").with(csrf())).andExpect(status().isOk())
				.andExpect(content().string("1"));
	}

	// ================= ERROR HISTORY TESTS =================

	@Test
	@WithMockUser
	public void testGetErrorHistory_Success() throws Exception {

		QuantityMeasurementDTO errorDto = new QuantityMeasurementDTO();
		errorDto.setOperation("add");
		errorDto.setErrorMessage("Cannot perform arithmetic between different measurement categories");
		errorDto.setError(true);

		List<QuantityMeasurementDTO> mockList = Arrays.asList(errorDto);

		Mockito.when(service.getErrorHistory()).thenReturn(mockList);

		mockMvc.perform(get("/api/v1/quantities/history/errored").with(csrf())).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].error").value(true)).andExpect(jsonPath("$[0].errorMessage").exists());
	}

	// ================= VALIDATION ERROR TESTS =================

	@Test
	@WithMockUser
	public void testCompareQuantities_InvalidUnit_Returns400() throws Exception {

		QuantityDTO q1 = new QuantityDTO(1.0, "FOOT", "LengthUnit");
		QuantityDTO q2 = new QuantityDTO(12.0, "INCHE", "LengthUnit");
		QuantityInputDTO input = new QuantityInputDTO(q1, q2, null);

		mockMvc.perform(post("/api/v1/quantities/compare").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isBadRequest());
	}

	@Test
	@WithMockUser
	public void testAddQuantities_IncompatibleTypes_Returns400() throws Exception {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 1.0, "KILOGRAM", "WeightUnit");

		QuantityMeasurementDTO errorResult = new QuantityMeasurementDTO();
		errorResult.setOperation("add");
		errorResult.setErrorMessage(
				"add Error: Cannot perform arithmetic between different measurement categories: LengthUnit and WeightUnit");
		errorResult.setError(true);

		Mockito.when(service.add(Mockito.any(QuantityInputDTO.class))).thenReturn(errorResult);

		mockMvc.perform(post("/api/v1/quantities/add").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input))).andExpect(status().isOk())
				.andExpect(jsonPath("$.error").value(true)).andExpect(jsonPath("$.errorMessage").exists());
	}

}
