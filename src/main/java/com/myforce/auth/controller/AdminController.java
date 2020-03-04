package com.myforce.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    public String admin() {
        return "admin can access this endpoint";
    }

    @PostMapping("/admin1")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    public String admin1() {
        return "admin1 can access this endpoint";
    }
}
