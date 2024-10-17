package io.hhplus.ecommerce.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.ecommerce.api.payment.application.PaymentDto;
import io.hhplus.ecommerce.api.payment.interfaces.PaymentController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("결제 API 테스트")
    public void ordersTest() throws Exception {
        PaymentDto.PaymentRequest request = new PaymentDto.PaymentRequest("U001", "O001");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
