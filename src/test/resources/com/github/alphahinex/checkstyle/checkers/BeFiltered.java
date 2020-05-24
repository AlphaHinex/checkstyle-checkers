package com.github.alphahinex.checkstyle.checkers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Not to be checked
 */
public class BeFilterd {

    @ApiOperation("blah blah")
    @PutMapping
    public String update(String id) {
        return "";
    }

    @GetMapping
    @ApiOperation("测试‍")
    public String get(String ids) {
        return "";
    }

    @ApiOperation("wa‍")
    @DeleteMapping
    public String delete(String id) {
        return "";
    }

    @PostMapping
    @ApiOperation("post")
    public String post() {
        return "";
    }

    @RequestMapping
    @ApiOperation("request")
    public String request() {
        return "";
    }

    @GetMapping
    public String errGet() {
        return "";
    }

    @PutMapping
    public String errPut() {
        return "";
    }

    @PostMapping
    public String errPost() {
        return "";
    }

    @DeleteMapping
    public String errDel() {
        return "";
    }

    @RequestMapping
    public String errReq() {
        return "";
    }

    private void priMethod1() {
        return;
    }

    private void priMethod2() {
        return;
    }

}
