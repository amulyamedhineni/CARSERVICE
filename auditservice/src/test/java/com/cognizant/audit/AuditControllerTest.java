package com.cognizant.audit;

import com.cognizant.audit.controller.AuditController;
import com.cognizant.audit.dto.AuditDto;
import com.cognizant.audit.entities.Audit;
import com.cognizant.audit.service.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditService servicerepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLogAction() throws Exception {
        AuditDto auditDto = new AuditDto(
                1L, 101L, "CREATE", LocalDateTime.now(), "admin", "Service Created"
        );

        Audit mockAudit = new Audit(
                1L, 1L, 101L, "CREATE", auditDto.getTimeStamp(), "admin", "Service Created"
        );

        when(servicerepo.logaction(
                Mockito.anyLong(),
                Mockito.anyLong(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()
        )).thenReturn(mockAudit);

        mockMvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auditDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carServiceID").value(1L))
                .andExpect(jsonPath("$.userId").value(101L))
                .andExpect(jsonPath("$.action").value("CREATE"))
                .andExpect(jsonPath("$.performedBy").value("admin"))
                .andExpect(jsonPath("$.details").value("Service Created"))
                .andDo(print());
    }

    @Test
    void testFetchLogs() throws Exception {
        Long carServiceId = 1L;

        Audit audit1 = new Audit(1L, carServiceId, 101L, "CREATE",
                LocalDateTime.now(), "admin", "Created a service");
        Audit audit2 = new Audit(2L, carServiceId, 102L, "UPDATE",
                LocalDateTime.now(), "admin", "Updated a service");

        List<Audit> audits = Arrays.asList(audit1, audit2);

        when(servicerepo.findByCarServiceId(carServiceId)).thenReturn(audits);

        mockMvc.perform(get("/api/fetchlogs/{carServiceID}", carServiceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].action").value("CREATE"))
                .andExpect(jsonPath("$[1].action").value("UPDATE"))
                .andDo(print());
    }
}
