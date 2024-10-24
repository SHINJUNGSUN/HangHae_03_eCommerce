package io.hhplus.ecommerce.user.interfaces;

import io.hhplus.ecommerce.common.annotation.CurrentUser;
import io.hhplus.ecommerce.user.application.UserFacade;
import io.hhplus.ecommerce.user.application.dto.*;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "사용자 API")
public class UserController {

    private final UserFacade userFacade;

    @Operation(summary = "회원가입 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSignUpResponse.class)))
    @PostMapping("/signup")
    public CommonApiResponse<UserSignUpResponse> signUp(@RequestBody UserSignUpRequest request) {
        return CommonApiResponse.success(userFacade.signUp(request));
    }

    @Operation(summary = "로그인 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginResponse.class)))
    @PostMapping("/login")
    public CommonApiResponse<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        return CommonApiResponse.success(userFacade.login(request));
    }

    @Operation(summary = "잔액 조회 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPointResponse.class)))
    @GetMapping("/point")
    public CommonApiResponse<UserPointResponse> getPoint(@Parameter(hidden = true) @CurrentUser() long userSeq) {
        return CommonApiResponse.success(userFacade.getPoint(userSeq));
    }

    @Operation(summary = "잔액 충전 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserPointResponse.class)))
    @PatchMapping("/point/charge")
    public CommonApiResponse<UserPointResponse> chargePoint(@Parameter(hidden = true) @CurrentUser() long userSeq, @RequestBody UserPointRequest request) {
        return CommonApiResponse.success(userFacade.chargePoint(userSeq, request));
    }
}
