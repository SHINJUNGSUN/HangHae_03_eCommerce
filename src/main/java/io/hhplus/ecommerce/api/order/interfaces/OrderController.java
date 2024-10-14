package io.hhplus.ecommerce.api.order.interfaces;

import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.api.order.application.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
@Tag(name = "주문 API")
public class OrderController {

    @Operation(summary = "주문 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.OrderResponse.class)))
    @PostMapping()
    public CommonApiResponse<OrderDto.OrderResponse> order(@RequestBody OrderDto.OrderRequest request) {

        List<OrderDto.ProductResponse> productList = new ArrayList<>();
        productList.add(OrderDto.ProductResponse.of("P001", "키보드", 1, 100000));
        productList.add(OrderDto.ProductResponse.of("P002", "마우스", 3, 50000));

        return CommonApiResponse.success(OrderDto.OrderResponse.of("O001", 250000, 1, productList));
    }
}

