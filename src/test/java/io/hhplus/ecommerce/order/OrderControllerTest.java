package io.hhplus.ecommerce.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.ecommerce.api.order.application.OrderApplicationService;
import io.hhplus.ecommerce.api.order.application.dto.OrderProduct;
import io.hhplus.ecommerce.api.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.api.order.interfaces.OrderController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderApplicationService OrderApplicationService;

    @Test
    @DisplayName("주문 API 테스트")
    public void ordersTest() throws Exception {

        OrderProduct orderProduct = new OrderProduct(1L, 1L);
        OrderRequest request = new OrderRequest(1L, List.of(orderProduct));

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
