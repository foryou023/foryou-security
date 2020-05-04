package com.foryou.security.core.controller;

import com.foryou.security.core.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ValidateController {


    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    @RequestMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws Exception {
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(type + "ValidateCodeProcessor");
        validateCodeProcessor.create(new ServletWebRequest(request,response));
    }



}
