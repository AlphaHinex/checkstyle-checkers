package com.github.alphahinex.checkstyle.checkers;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * Pass check
 */
@Api(tags = "/restController")
@RestController
public class PassController {

    @ApiOperation("blah blah blah")
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

    @SuppressWarnings("unchecked")
    private void priMethod1() {
        return;
    }

    private void priMethod2() {
        return;
    }

    public static class AdminCatalogVO {

        @ApiModelProperty(name = "‍编码", required = true)
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return JSONUtil.toJSONIgnoreException(this);
        }
    }
}
