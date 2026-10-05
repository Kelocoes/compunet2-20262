package com.example.teacher.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc/public")
public class PublicMVCController {

    @GetMapping
    public String publicEndPoint() {
        return "public";
    }
}
