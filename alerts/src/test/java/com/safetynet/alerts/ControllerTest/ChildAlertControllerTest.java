//package com.safetynet.alerts.ControllerTest;
//package com.safetynet.alerts.ControllerTest;
//
//import com.safetynet.alerts.controller.ChildAlertController;
//import com.safetynet.alerts.domain.ChildAlert;
//import com.safetynet.alerts.service.ChildAlertService;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static java.lang.reflect.Array.get;
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@WebMvcTest(ChildAlertController.class)
//public class ChildAlertControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ChildAlertService childAlertServiceMock;
//
//    @Test
//    public void testGetChildInfoByAddressFromChildAlertService() throws Exception {
//        // Given
//        ChildAlert child1 = new ChildAlert("John", "Doe", 10, List.of("Jane Doe"));
//        ChildAlert child2 = new ChildAlert("Sarah", "Smith", 7, List.of("Bob Smith"));
//
//        List<ChildAlert> mockResponse = Arrays.asList(child1, child2);
//        String address = "123 Oak Street";
//
//        when(childAlertServiceMock.getChildInfoByAddress(address)).thenReturn(mockResponse);
//
//        mockMvc.perform(get( address))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("John")))
//                .andExpect(content().string(containsString("Alice")));
//    }
//}





