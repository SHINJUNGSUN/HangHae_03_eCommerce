package io.hhplus.ecommerce.api.product.interfaces;

import io.hhplus.ecommerce.api.product.application.ProductUseCase;
import io.hhplus.ecommerce.api.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Tag(name = "상품 API")
public class ProductController {

    private final ProductUseCase productUseCase;

    @Operation(summary = "상품 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
    @GetMapping("")
    public CommonApiResponse<List<ProductResponse>> products() {
        return CommonApiResponse.success(productUseCase.products());
    }

    @Operation(summary = "상위 상품 조회 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
    @GetMapping("/popular")
    public CommonApiResponse<List<ProductResponse>> topProducts() {
        return CommonApiResponse.success(productUseCase.popularProducts());
    }
}
