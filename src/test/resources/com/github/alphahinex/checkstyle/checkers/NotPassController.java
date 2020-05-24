package com.github.alphahinex.checkstyle.checkers;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Not pass check because no swagger annotation on methods
 * There are 5 issues in this file (the 5 methods with annotation)
 */
@Api(tags = "/controller")
@Controller
public class NotPassController {

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

    public String notBeChecked() {
        return "";
    }

    private void priMethod1() {
        return;
    }

    private void priMethod2() {
        return;
    }

}
