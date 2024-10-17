package io.hhplus.ecommerce.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.ecommerce.api.cart.application.CartApplicationService;
import io.hhplus.ecommerce.api.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.api.cart.interfaces.CartController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartApplicationService cartApplicationService;

    @Test
    @DisplayName("장바구니 목록 조회 API 테스트")
    public void cartsTest() throws Exception {
        long userId = 1L;

        mockMvc.perform(get("/carts/{id}", userId))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("장바구니 추가 API 테스트")
    public void addTest() throws Exception {
        CartAddRequest request = new CartAddRequest(1L, 1L, 1L);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(patch("/carts/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("장바구니 제거 API 테스트")
    public void removeTest() throws Exception {
        CartRemoveRequest request = new CartRemoveRequest(1L, 1L);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(patch("/carts/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
