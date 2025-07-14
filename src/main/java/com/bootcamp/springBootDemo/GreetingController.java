package com.bootcamp.springBootDemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public String sayHelloWord() {
        return "Hello, World";
    }
}
