package io.hhplus.ecommerce.order.interfaces;

import io.hhplus.ecommerce.common.annotation.CurrentUser;
import io.hhplus.ecommerce.order.application.OrderFacade;
import io.hhplus.ecommerce.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
@Tag(name = "주문 API")
public class OrderController {

    private final OrderFacade orderFacade;

    @Operation(summary = "주문 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    @PostMapping("")
    public CommonApiResponse<OrderResponse> order(@Parameter(hidden = true) @CurrentUser long userSeq, @RequestBody OrderRequest request) {
        return CommonApiResponse.success(orderFacade.createOrder(userSeq, request));
    }
}

