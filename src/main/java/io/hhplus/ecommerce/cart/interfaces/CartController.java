package io.hhplus.ecommerce.cart.interfaces;

import io.hhplus.ecommerce.cart.application.CartFacade;
import io.hhplus.ecommerce.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.cart.application.dto.CartResponse;
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

    private final CartFacade cartFacade;

    @Operation(summary = "장바구니 목록 조회 API")
    @Parameter(name = "userSeq", description = "사용자 고유 식별자")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartResponse.class))))
    @GetMapping("/{userSeq}")
    public CommonApiResponse<List<CartResponse>> getCarts(@PathVariable() long userSeq) {
        return CommonApiResponse.success(cartFacade.getCarts(userSeq));
    }

    @Operation(summary = "장바구니 추가 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartResponse.class))))
    @PostMapping()
    public CommonApiResponse<List<CartResponse>> postCarts(@RequestBody CartAddRequest request) {
        return CommonApiResponse.success(cartFacade.addCart(request));
    }

    @Operation(summary = "장바구니 제거 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartResponse.class))))
    @DeleteMapping()
    public CommonApiResponse<List<CartResponse>> deleteCarts(@RequestBody CartRemoveRequest request) {
        return CommonApiResponse.success(cartFacade.removeCart(request));
    }
}
