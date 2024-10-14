package io.hhplus.ecommerce.api.user.interfaces;

import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.api.user.application.UserDto;
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

    @Operation(summary = "잔액 조회 API")
    @Parameter(name = "userTsid", description = "사용자 식별 ID")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.BalanceResponse.class)))
    @GetMapping("/balance/{userTsid}")
    public CommonApiResponse<UserDto.BalanceResponse> balance(@PathVariable String userTsid) {

        return CommonApiResponse.success(UserDto.BalanceResponse.of(userTsid, 1000000L));
    }

    @Operation(summary = "잔액 충전 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.BalanceResponse.class)))
    @PatchMapping("/balance/charge")
    public CommonApiResponse<UserDto.BalanceResponse> charge(@RequestBody UserDto.BalanceUpdateRequest request) {

        return CommonApiResponse.success(UserDto.BalanceResponse.of(request.userTsid(), 1000000L + request.amount()));
    }
}
