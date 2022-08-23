package com.turing.controller;

import com.turing.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/createOrder")
    public String createOrder(){
        orderService.createOrder();
        return "ok";
    }

}
