package com.github.julianomachadoo.pessoasapi.rest.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IndexController {

    @GetMapping(value = "/")
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/pessoas");
        httpServletResponse.setStatus(302);
    }
}
