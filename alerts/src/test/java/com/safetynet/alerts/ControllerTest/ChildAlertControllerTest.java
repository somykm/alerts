package com.safetynet.alerts.ControllerTest;

import com.safetynet.alerts.controller.ChildAlertController;
import com.safetynet.alerts.domain.ChildAlert;
import com.safetynet.alerts.service.ChildAlertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChildAlertController.class)
public class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChildAlertService childAlertServiceMock;

    @Test
    public void testGetChildInfoByAddressFromChildAlertService() throws Exception {
        ChildAlert child1 = new ChildAlert("John", "Doe", 10, List.of("Jane Doe"));
        ChildAlert child2 = new ChildAlert("Sarah", "Smith", 7, List.of("Bob Smith"));

        List<ChildAlert> mockResponse = Arrays.asList(child1, child2);
        String address = "123 Oak Street";

        when(childAlertServiceMock.getChildInfoByAddress(address)).thenReturn(mockResponse);

        mockMvc.perform(get("/childAlert")
                        .param("address", "123 Oak Street"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")))
                .andExpect(content().string(containsString("Sarah")));
    }
}