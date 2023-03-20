package ru.chislab.fireSystemTester.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/")
public class ChapterController {

    @CrossOrigin(origins = "*")
    @GetMapping(path = "")
    public String getChapters() {
        System.out.println("foo");
        return "index";
    }
}
