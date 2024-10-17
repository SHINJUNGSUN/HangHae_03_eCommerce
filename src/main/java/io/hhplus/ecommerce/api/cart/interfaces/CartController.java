package io.hhplus.ecommerce.api.cart.interfaces;

import io.hhplus.ecommerce.api.cart.application.CartApplicationService;
import io.hhplus.ecommerce.api.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carts")
@RequiredArgsConstructor
@Tag(name = "장바구니 API")
public class CartController {

    private final CartApplicationService cartApplicationService;

    @Operation(summary = "장바구니 목록 조회 API")
    @Parameter(name = "id", description = "사용자 고유 식별자")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartResponse.class))))
    @GetMapping("/{id}")
    public CommonApiResponse<List<CartResponse>> carts(@PathVariable("id") long userId) {
        return CommonApiResponse.success(cartApplicationService.getCarts(userId));
    }

    @Operation(summary = "장바구니 추가 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartResponse.class))))
    @PatchMapping("/add")
    public CommonApiResponse<List<CartResponse>> add(@RequestBody CartAddRequest request) {
        return CommonApiResponse.success(cartApplicationService.addCart(request));
    }

    @Operation(summary = "장바구니 제거 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartResponse.class))))
    @PatchMapping("/remove")
    public CommonApiResponse<List<CartResponse>> remove(@RequestBody CartRemoveRequest request) {
        return CommonApiResponse.success(cartApplicationService.removeCart(request));
    }
}
