package com.hmall.api.client;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserClient {
//    @ApiOperation("扣减余额")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pw", value = "支付密码"),
//            @ApiImplicitParam(name = "amount", value = "支付金额")
//    })
    @PutMapping("/users/money/deduct")
     void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);
}
