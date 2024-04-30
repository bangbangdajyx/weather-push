package com.cws.controller;


import com.cws.utils.PushUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试手动推送
 */
@Api(tags = "推送")
@RestController
public class TestController {

    //http://localhost:8085/test
    @ApiOperation("推送信息")
    @GetMapping("test")
    public String test() {
        return PushUtil.push();
    }

}
