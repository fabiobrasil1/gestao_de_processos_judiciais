package com.ms.analise_tecnica_po.useCases.process;

import com.fasterxml.jackson.databind.ObjectMapper;
// import com.ms.analise_tecnica_po.config.TestDataInitializer;
import com.ms.analise_tecnica_po.domain.process.controllers.dtos.AddDefendantDto;
import com.ms.analise_tecnica_po.domain.process.controllers.dtos.ProcessRecordDto;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EndToEndTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
  public void testRegisterProcessEndToEnd() throws Exception {
    UUID userId = UUID.fromString("1b924f50-3bf0-46bf-899d-8a1059193240");
    String description = "processo description";
    String processNumber = "12345";
    mockMvc.perform(post("/process")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new ProcessRecordDto(userId, description, processNumber))))
        .andExpect(status().isCreated())
        .andExpect(content().string(notNullValue()));
  }

  @Test
  @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
  public void testRetrieveUserProcesses() throws Exception {
    UUID userId = UUID.fromString("1b924f50-3bf0-46bf-899d-8a1059193240");

    mockMvc.perform(get("/process/user/{userId}", userId))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("{\"processNumbers\":[\"12345\"]}"));
  }

  @Test
  @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
  public void testAddDefendantEndToEnd() throws Exception {
    String processNumber = "12345";
    String defendantName = "defendant name";

    mockMvc.perform(post("/process/{processNumber}/defendants", processNumber)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(new AddDefendantDto(defendantName))))
        .andExpect(status().isOk())
        .andExpect(content().string(notNullValue()));
  }

  @Test
  @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
  public void deleteProcesses() throws Exception {
    UUID userId = UUID.fromString("1b924f50-3bf0-46bf-899d-8a1059193240");
    String processNUmber = "12345";
    mockMvc.perform(delete("/process/{userId}/{processNumber}", userId, processNUmber))
        .andExpect(status().isNoContent());
  }

  private String asJsonString(Object obj) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }
}
