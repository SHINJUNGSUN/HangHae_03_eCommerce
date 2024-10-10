package io.hhplus.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hhplus.ecommerce.user.application.UserDto;
import io.hhplus.ecommerce.user.presentation.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("잔액 조회 API 테스트")
    public void balanceTest() throws Exception {
        String userTsid = "U001";

        mockMvc.perform(get("/users/balance/{userTsid}", userTsid))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("잔액 충전 API 테스트")
    public void chargeTest() throws Exception {
        UserDto.BalanceChargeRequest request = new UserDto.BalanceChargeRequest("U001", 100000L);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(patch("/users/balance/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
