package com.example.rohlik.controllers;

import com.example.rohlik.models.ItemInOrder;
import com.example.rohlik.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody List<ItemInOrder> itemsInOrder){
        return orderService.createOrder(itemsInOrder);
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<Object> cancelOrder(@PathVariable long id){
        return orderService.cancelOrder(id);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<Object> paymentOrder(@PathVariable long id){
        return orderService.paymentOrder(id);
    }

}