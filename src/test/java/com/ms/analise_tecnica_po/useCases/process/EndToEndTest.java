package com.ms.analise_tecnica_po.useCases.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.analise_tecnica_po.domain.process.controllers.dtos.AddDefendantDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class EndToEndTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testAddDefendantEndToEnd() throws Exception {
    String processNumber = "12345";
    String defendantName = "defendant name";

    mockMvc.perform(post("/process/{processNumber}/defendants", processNumber)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new AddDefendantDto(defendantName))))
        .andExpect(status().isOk())
        .andExpect(content().string(notNullValue()));
  }

  private String asJsonString(Object obj) throws Exception {
    return objectMapper.writeValueAsString(obj);
  }
}
