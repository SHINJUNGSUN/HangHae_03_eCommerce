package io.hhplus.ecommerce.user.interfaces;

import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.user.application.service.UserPointApplicationService;
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

    private final UserPointApplicationService userPointApplicationService;

    @Operation(summary = "잔액 조회 API")
    @Parameter(name = "userSeq", description = "사용자 고유 식별자")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPointResponse.class)))
    @GetMapping("/point/{userSeq}")
    public CommonApiResponse<UserPointResponse> getPoint(@PathVariable() long userSeq) {
        return CommonApiResponse.success(userPointApplicationService.getPoint(userSeq));
    }

    @Operation(summary = "잔액 충전 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPointResponse.class)))
    @PatchMapping("/balance/charge")
    public CommonApiResponse<UserPointResponse> charge(@RequestBody UserPointRequest request) {
        return CommonApiResponse.success(userPointApplicationService.chargePoint(request));
    }
}
