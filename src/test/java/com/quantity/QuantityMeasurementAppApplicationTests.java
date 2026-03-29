package com.quantity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;
import com.quantity.model.QuantityMeasurementEntity;
import com.quantity.repository.QuantityMeasurementRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuantityMeasurementAppApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private QuantityMeasurementRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	private String baseUrl;
	private HttpHeaders headers;

	@BeforeEach
	public void setUp() {
		baseUrl = "http://localhost:" + port + "/api/v1/quantities";
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		repository.deleteAll();
	}

	// ===== Helper methods =====

	private QuantityInputDTO buildInput(double v1, String u1, String t1, double v2, String u2, String t2) {
		QuantityDTO q1 = new QuantityDTO(v1, u1, t1);
		QuantityDTO q2 = new QuantityDTO(v2, u2, t2);
		return new QuantityInputDTO(q1, q2, null);
	}

	// ================= SPRING BOOT APPLICATION STARTS =================

	@Test
	public void testSpringBootApplicationStarts() {
		assertNotNull(restTemplate);
		assertNotNull(repository);
	}

	// ================= COMPARE TESTS =================

	@Test
	public void testRestEndpointCompareQuantities() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(baseUrl + "/compare", HttpMethod.POST,
				request, QuantityMeasurementDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("compare", response.getBody().getOperation());
		assertEquals("true", response.getBody().getResultString());
		assertFalse(response.getBody().isError());
	}

	// ================= CONVERT TESTS =================

	@Test
	public void testRestEndpointConvertQuantities() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 0.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(baseUrl + "/convert", HttpMethod.POST,
				request, QuantityMeasurementDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("convert", response.getBody().getOperation());
		assertEquals(12.0, response.getBody().getResultValue());
		assertFalse(response.getBody().isError());
	}

	// ================= ADD TESTS =================

	@Test
	public void testRestEndpointAddQuantities() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(baseUrl + "/add", HttpMethod.POST,
				request, QuantityMeasurementDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("add", response.getBody().getOperation());
		assertEquals(2.0, response.getBody().getResultValue());
		assertEquals("FEET", response.getBody().getResultUnit());
		assertFalse(response.getBody().isError());
	}

	// ================= SUBTRACT TESTS =================

	@Test
	public void testRestEndpointSubtractQuantities() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 6.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(baseUrl + "/subtract", HttpMethod.POST,
				request, QuantityMeasurementDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("subtract", response.getBody().getOperation());
		assertEquals(0.5, response.getBody().getResultValue());
		assertEquals("FEET", response.getBody().getResultUnit());
		assertFalse(response.getBody().isError());
	}

	// ================= DIVIDE TESTS =================

	@Test
	public void testRestEndpointDivideQuantities() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(baseUrl + "/divide", HttpMethod.POST,
				request, QuantityMeasurementDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("divide", response.getBody().getOperation());
		assertFalse(response.getBody().isError());
	}

	// ================= INVALID INPUT RETURNS 400 =================

	@Test
	public void testRestEndpointInvalidInput_Returns400() {

		QuantityDTO q1 = new QuantityDTO(1.0, "FOOT", "LengthUnit");
		QuantityDTO q2 = new QuantityDTO(12.0, "INCHE", "LengthUnit");
		QuantityInputDTO input = new QuantityInputDTO(q1, q2, null);

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/compare", HttpMethod.POST, request,
				String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	// ================= H2 DATABASE PERSISTENCE =================

	@Test
	public void testH2DatabasePersistence() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/add", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		List<QuantityMeasurementEntity> entities = repository.findAll();
		assertFalse(entities.isEmpty());
		assertEquals("add", entities.get(0).getOperation());
	}

	// ================= HISTORY BY OPERATION =================

	@Test
	public void testGetHistoryByOperation() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/compare", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		ResponseEntity<List> response = restTemplate.exchange(baseUrl + "/history/operation/compare", HttpMethod.GET,
				new HttpEntity<>(headers), List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertFalse(response.getBody().isEmpty());
	}

	// ================= HISTORY BY MEASUREMENT TYPE =================

	@Test
	public void testGetHistoryByMeasurementType() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/add", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		ResponseEntity<List> response = restTemplate.exchange(baseUrl + "/history/type/LengthUnit", HttpMethod.GET,
				new HttpEntity<>(headers), List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertFalse(response.getBody().isEmpty());
	}

	// ================= OPERATION COUNT =================

	@Test
	public void testGetOperationCount() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/compare", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		ResponseEntity<Long> response = restTemplate.exchange(baseUrl + "/count/compare", HttpMethod.GET,
				new HttpEntity<>(headers), Long.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody() >= 1L);
	}

	// ================= ERROR HISTORY =================

	@Test
	public void testGetErrorHistory() {

		QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
		QuantityDTO q2 = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
		QuantityInputDTO input = new QuantityInputDTO(q1, q2, null);

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/add", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		ResponseEntity<List> response = restTemplate.exchange(baseUrl + "/history/errored", HttpMethod.GET,
				new HttpEntity<>(headers), List.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertFalse(response.getBody().isEmpty());
	}

	// ================= ACTUATOR HEALTH =================

	@Test
	public void testActuatorHealthEndpoint() {

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/actuator/health",
				HttpMethod.GET, new HttpEntity<>(headers), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("UP"));
	}

	// ================= SWAGGER UI LOADS =================

	@Test
	public void testSwaggerUILoads() {

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/swagger-ui/index.html",
				HttpMethod.GET, new HttpEntity<>(headers), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	// ================= JPA REPOSITORY findByOperation =================

	@Test
	public void testJPARepositoryFindByOperation() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/add", HttpMethod.POST, request, QuantityMeasurementDTO.class);
		restTemplate.exchange(baseUrl + "/compare", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		List<QuantityMeasurementEntity> addResults = repository.findByOperation("add");
		List<QuantityMeasurementEntity> compareResults = repository.findByOperation("compare");

		assertFalse(addResults.isEmpty());
		assertFalse(compareResults.isEmpty());
		assertEquals("add", addResults.get(0).getOperation());
		assertEquals("compare", compareResults.get(0).getOperation());
	}

	// ================= MULTIPLE OPERATIONS INTEGRATION =================

	@Test
	public void testIntegrationTest_MultipleOperations() {

		QuantityInputDTO input = buildInput(1.0, "FEET", "LengthUnit", 12.0, "INCHES", "LengthUnit");

		HttpEntity<QuantityInputDTO> request = new HttpEntity<>(input, headers);

		restTemplate.exchange(baseUrl + "/compare", HttpMethod.POST, request, QuantityMeasurementDTO.class);
		restTemplate.exchange(baseUrl + "/convert", HttpMethod.POST, request, QuantityMeasurementDTO.class);
		restTemplate.exchange(baseUrl + "/add", HttpMethod.POST, request, QuantityMeasurementDTO.class);
		restTemplate.exchange(baseUrl + "/subtract", HttpMethod.POST, request, QuantityMeasurementDTO.class);
		restTemplate.exchange(baseUrl + "/divide", HttpMethod.POST, request, QuantityMeasurementDTO.class);

		List<QuantityMeasurementEntity> all = repository.findAll();
		assertEquals(5, all.size());
	}

}