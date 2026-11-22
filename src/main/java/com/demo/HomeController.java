
package com.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("title",
                "DevOps Learning Hub");

        model.addAttribute("courses", new String[]{
                "Java Programming",
                "Maven",
                "Jenkins",
                "Docker",
                "Kubernetes",
                "SonarQube",
                "Nexus Repository"
        });

        return "index";
    }
}
