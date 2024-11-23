package com.charann.InventoryManagementSystem.controller;

import com.charann.InventoryManagementSystem.entity.Distribution;
import com.charann.InventoryManagementSystem.entity.InProduct;
import com.charann.InventoryManagementSystem.entity.OrderStatus;
import com.charann.InventoryManagementSystem.repository.OrderStatusRepo;
import com.charann.InventoryManagementSystem.service.DistributeStockService;
import com.charann.InventoryManagementSystem.service.InProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    InProductService inProductService;

    @Autowired
    DistributeStockService distributeStockService;

    @Autowired
    OrderStatusRepo orderStatusRepo;

    @PostMapping("/add")
    public ResponseEntity<InProduct> addProduct(@RequestBody InProduct inProduct) {

        return new ResponseEntity<>(
            inProductService.input(inProduct),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/distribute")
    public ResponseEntity<String> distribute(@RequestBody Distribution distribution) {

        return new ResponseEntity<>(
                distributeStockService.distributeProduct(distribution),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/order-status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable String id) {
        return new ResponseEntity<>(
                distributeStockService.getOrderStatus(id),
                HttpStatus.ACCEPTED
        );
    }

    @PutMapping("/update-status/{id}")
    public String changeOrderStatus(@PathVariable String id, @RequestBody String status) {
        OrderStatus orderStatus = orderStatusRepo.getByOrderId(id);
        orderStatus.setOrderStatus(status);
        orderStatusRepo.save(orderStatus);

        return "Status updated";
    }


}
