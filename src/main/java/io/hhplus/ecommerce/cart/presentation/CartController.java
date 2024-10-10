package io.hhplus.ecommerce.cart.presentation;

import io.hhplus.ecommerce.cart.application.CartDto;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("carts")
@RequiredArgsConstructor
@Tag(name = "장바구니 API")
public class CartController {

    @Operation(summary = "장바구니 목록 조회 API")
    @Parameter(name = "userTsid", description = "사용자 고유 ID")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartDto.CartResponse.class))))
    @GetMapping("/{userTsid}")
    public CommonApiResponse<List<CartDto.CartResponse>> carts(@PathVariable String userTsid) {

        List<CartDto.CartResponse> data = new ArrayList<>();
        data.add(new CartDto.CartResponse("C001", "P001", "키보드", 1, 120000));
        data.add(new CartDto.CartResponse("C003", "P003", "해드셋", 2, 80000));

        return CommonApiResponse.success(data);
    }

    @Operation(summary = "장바구니 추가 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartDto.CartResponse.class))))
    @PatchMapping("/add")
    public CommonApiResponse<List<CartDto.CartResponse>> add(@RequestBody CartDto.CartAddRequest request) {

        List<CartDto.CartResponse> data = new ArrayList<>();
        data.add(new CartDto.CartResponse("C001", "P001", "키보드", 1, 120000));
        data.add(new CartDto.CartResponse("C002", "P002", "마우스", 4, 50000));
        data.add(new CartDto.CartResponse("C003", "P003", "해드셋", 2, 80000));

        return CommonApiResponse.success(data);
    }

    @Operation(summary = "장바구니 제거 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CartDto.CartResponse.class))))
    @PatchMapping("/remove")
    public CommonApiResponse<List<CartDto.CartResponse>> remove(@RequestBody CartDto.CartRemoveRequest request) {

        List<CartDto.CartResponse> data = new ArrayList<>();
        data.add(new CartDto.CartResponse("C001", "P001", "키보드", 1, 120000));
        data.add(new CartDto.CartResponse("C002", "P002", "마우스", 4, 50000));

        return CommonApiResponse.success(data);
    }
}
