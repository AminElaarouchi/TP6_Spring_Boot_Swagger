package com.example.studentmanagement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:tp6;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class StudentApiIntegrationTest {
    @Autowired MockMvc mvc;

    @Test void servesRestEndpointsAndSwagger() throws Exception {
        mvc.perform(post("/students/save").contentType("application/json")
                .content("{\"nom\":\"LACHGAR\",\"prenom\":\"Mohamed\",\"dateNaissance\":\"1985-09-01\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNumber());
        mvc.perform(get("/students/count")).andExpect(status().isOk()).andExpect(jsonPath("$").value(1));
        mvc.perform(get("/students/byYear")).andExpect(status().isOk()).andExpect(jsonPath("$[0][0]").value(1985));
        mvc.perform(get("/v3/api-docs")).andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/students/all']").exists());
    }
}
