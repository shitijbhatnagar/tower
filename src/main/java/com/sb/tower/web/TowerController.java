package com.sb.tower.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tower")
public class TowerController {

    @GetMapping
    public String processEchoMessage() {
        Logger.getLogger(TowerController.class.getName()).info("Tower Service is Up");
        return "Tower Service is Up";
    }
}