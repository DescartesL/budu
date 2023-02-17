package com.eula.budu.controller;

import com.eula.budu.annotation.ApiVersion;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DesLUO
 */
@RestController
@RequestMapping("api/{v}")
public class TestController {

    @ApiVersion("1.0.0")
    @RequestMapping("get")
    public Integer get(){
        return 1;
    }

    @ApiVersion("1.1.1")
    @RequestMapping("get")
    public Integer get2(){
        return 2;
    }

    @ApiVersion("1.2.2")
    @RequestMapping("get")
    public Integer get3(){
        return 3;
    }
}
