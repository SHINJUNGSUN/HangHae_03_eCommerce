package io.hhplus.ecommerce.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.ecommerce.api.cart.application.CartDto;
import io.hhplus.ecommerce.api.cart.interfaces.CartController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

    @Test
    @DisplayName("장바구니 목록 조회 API 테스트")
    public void cartsTest() throws Exception {
        String userTsid = "U001";

        mockMvc.perform(get("/carts/{userTsid}", userTsid))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("장바구니 추가 API 테스트")
    public void addTest() throws Exception {
        CartDto.CartAddRequest request = new CartDto.CartAddRequest("U001", "P002", 4);

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
        CartDto.CartRemoveRequest request = new CartDto.CartRemoveRequest("U001", "C003");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(patch("/carts/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
