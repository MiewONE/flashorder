package com.apache.gradle.plugins.flashorder.Controller;

import com.apache.gradle.plugins.flashorder.Service.DetectText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class Index {


    @GetMapping("/")
    public String home() throws IOException {

        return "home";
    }
}
