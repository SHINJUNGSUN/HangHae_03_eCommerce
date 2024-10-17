package io.hhplus.ecommerce.api.payment.interfaces;

import io.hhplus.ecommerce.api.payment.application.PaymentApplicationService;
import io.hhplus.ecommerce.api.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.api.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
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

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
@Tag(name = "결제 API")
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    @Operation(summary = "결제 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class)))
    @PostMapping()
    public CommonApiResponse<PaymentResponse> payment(@RequestBody PaymentRequest request) {
        return CommonApiResponse.success(paymentApplicationService.payment(request));
    }
}
