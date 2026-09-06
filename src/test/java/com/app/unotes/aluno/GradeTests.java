package com.app.unotes.aluno;

import com.app.unotes.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class GradeTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GradeService gradeService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    //Métodos---------------------

    //---------------------



}
