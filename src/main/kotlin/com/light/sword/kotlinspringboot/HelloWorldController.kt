package com.light.sword.kotlinspringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping(value = ["", "/", "/hello"])
    fun hello(): String {
        return "Hello World"
    }
}