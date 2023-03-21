package br.com.victorlc.forum.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloController {

    fun hello() = "Hello, World!"
}