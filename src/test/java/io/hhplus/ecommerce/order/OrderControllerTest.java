package io.hhplus.ecommerce.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.ecommerce.api.order.application.OrderDto;
import io.hhplus.ecommerce.api.order.interfaces.OrderController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

    @Test
    @DisplayName("주문 API 테스트")
    public void ordersTest() throws Exception {
        List<OrderDto.ProductRequest> productList = new ArrayList<>();
        productList.add(new OrderDto.ProductRequest("P001", 1));
        productList.add(new OrderDto.ProductRequest("P002", 3));

        OrderDto.OrderRequest request = new OrderDto.OrderRequest("U001", productList);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
