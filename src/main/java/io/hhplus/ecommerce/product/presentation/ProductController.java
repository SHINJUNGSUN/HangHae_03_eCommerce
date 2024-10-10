package io.hhplus.ecommerce.product.presentation;

import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.product.application.ProductDto;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Tag(name = "상품 API")
public class ProductController {

    @Operation(summary = "상품 목록 조회 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.ProductResponse.class))))
    @GetMapping()
    public CommonApiResponse<List<ProductDto.ProductResponse>> products() {

        List<ProductDto.ProductResponse> data = new ArrayList<>();
        data.add(ProductDto.ProductResponse.of("P001", "키보드", 100000, 100));
        data.add(ProductDto.ProductResponse.of("P002", "마우스", 50000, 500));
        data.add(ProductDto.ProductResponse.of("P003", "모니터", 300000, 90));
        data.add(ProductDto.ProductResponse.of("P004", "헤드셋", 150000, 200));
        data.add(ProductDto.ProductResponse.of("P005", "노트북", 1000000, 30));
        data.add(ProductDto.ProductResponse.of("P006", "태블릿", 250000, 40));
        data.add(ProductDto.ProductResponse.of("P007", "데스크톱", 2000000, 10));

        return CommonApiResponse.success(data);
    }

    @Operation(summary = "상위 상품 조회 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.ProductResponse.class))))
    @GetMapping("/top")
    public CommonApiResponse<List<ProductDto.ProductResponse>> topProducts() {

        List<ProductDto.ProductResponse> data = new ArrayList<>();
        data.add(ProductDto.ProductResponse.of("P001", "키보드", 100000, 100));
        data.add(ProductDto.ProductResponse.of("P002", "마우스", 50000, 500));
        data.add(ProductDto.ProductResponse.of("P003", "모니터", 300000, 90));
        data.add(ProductDto.ProductResponse.of("P004", "헤드셋", 150000, 200));
        data.add(ProductDto.ProductResponse.of("P005", "노트북", 1000000, 30));

        return CommonApiResponse.success(data);
    }
}
