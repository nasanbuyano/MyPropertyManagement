package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
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
