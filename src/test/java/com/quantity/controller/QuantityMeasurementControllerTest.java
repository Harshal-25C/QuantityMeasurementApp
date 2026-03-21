package com.quantity.controller;

import com.quantity.service.IQuantityMeasurementService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    // ================= COMPARE =================

    @Test
    @WithMockUser
    void testCompareAPI() throws Exception {

        String json = """
        {
          "thisQuantityDTO":{"value":1,"unit":"FEET"},
          "thatQuantityDTO":{"value":12,"unit":"INCHES"}
        }
        """;

        mockMvc.perform(post("/api/v1/quantities/compare")
                .with(csrf())
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

    // ================= ADD =================

    @Test
    @WithMockUser
    void testAddAPI() throws Exception {

        String json = """
        {
          "thisQuantityDTO":{"value":1,"unit":"FEET"},
          "thatQuantityDTO":{"value":12,"unit":"INCHES"}
        }
        """;

        mockMvc.perform(post("/api/v1/quantities/add")
                .with(csrf())
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

    // ================= SUBTRACT =================

    @Test
    @WithMockUser
    void testSubtractAPI() throws Exception {

        String json = """
        {
          "thisQuantityDTO":{"value":2,"unit":"FEET"},
          "thatQuantityDTO":{"value":12,"unit":"INCHES"}
        }
        """;

        mockMvc.perform(post("/api/v1/quantities/subtract")
                .with(csrf())
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

    // ================= DIVIDE =================

    @Test
    @WithMockUser
    void testDivideAPI() throws Exception {

        String json = """
        {
          "thisQuantityDTO":{"value":12,"unit":"INCHES"},
          "thatQuantityDTO":{"value":1,"unit":"FEET"}
        }
        """;

        mockMvc.perform(post("/api/v1/quantities/divide")
                .with(csrf())
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

    // ================= CONVERT =================

    @Test
    @WithMockUser
    void testConvertAPI() throws Exception {

        String json = """
        {
          "value":1,
          "unit":"FEET"
        }
        """;

        mockMvc.perform(post("/api/v1/quantities/convert/INCHES")
                .with(csrf())
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

}