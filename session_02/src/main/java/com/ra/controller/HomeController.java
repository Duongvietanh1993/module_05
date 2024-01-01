package com.ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 60764e28a77918b08827d1eb21adee3312f2360c
