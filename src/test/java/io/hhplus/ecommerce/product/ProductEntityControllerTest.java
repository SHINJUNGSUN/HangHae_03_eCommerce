package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.product.application.service.ProductApplicationService;
import io.hhplus.ecommerce.product.interfaces.ProductController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductApplicationService productApplicationService;

    @Test
    @DisplayName("상품 목록 조회 API 테스트")
    public void productsTest() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상위 상품 조회 API 테스트")
    public void popularProductsTest() throws Exception {
        mockMvc.perform(get("/products/popular"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
