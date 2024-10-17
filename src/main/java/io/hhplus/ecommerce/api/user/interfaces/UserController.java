package io.hhplus.ecommerce.api.user.interfaces;

import io.hhplus.ecommerce.api.user.application.UserApplicationService;
import io.hhplus.ecommerce.api.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.api.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "사용자 API")
public class UserController {

    private final UserApplicationService userApplicationService;

    @Operation(summary = "잔액 조회 API")
    @Parameter(name = "id", description = "사용자 고유 식별자")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPointResponse.class)))
    @GetMapping("/balance/{id}")
    public CommonApiResponse<UserPointResponse> balance(@PathVariable("id") long userId) {
        return CommonApiResponse.success(userApplicationService.getPoint(userId));
    }

    @Operation(summary = "잔액 충전 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPointResponse.class)))
    @PatchMapping("/balance/charge")
    public CommonApiResponse<UserPointResponse> charge(@RequestBody UserPointRequest request) {
        return CommonApiResponse.success(userApplicationService.chargePoint(request));
    }
}
