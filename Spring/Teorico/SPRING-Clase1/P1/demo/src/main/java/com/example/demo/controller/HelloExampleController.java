package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;


@RestController
public class HelloExampleController {

    @GetMapping(path="sayHello/{name}")
    public String sayHello(@PathVariable String name){
        return "Hello "+name+"!";
    }

    @GetMapping(path="sayHello")
    public String sayHello2(@RequestParam String name){
        return "Hello "+name+"!";
    }

    /*
    @PostMapping
    public String sayHello(@PathVariable String string){
        return "Hello World!";
    }
     */
}
