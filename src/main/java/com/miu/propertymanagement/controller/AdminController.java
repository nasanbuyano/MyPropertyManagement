package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("admin/{id}")
    ResponseEntity<?> getUserById(@PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("admin/{id}/changeStatus")
    ResponseEntity<?> changeStatusByUser(@PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(userService.changeStatusByUser(id));
    }

    @GetMapping("admin/changeStatusRequests")
    ResponseEntity<?> changeStatusRequests() {
        return ResponseEntity.ok(userService.changeStatusRequests());
    }
}
