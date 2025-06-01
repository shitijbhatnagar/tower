package com.sb.tower.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/tower")
public class TowerController {

    private static final Logger LOG = LoggerFactory.getLogger(TowerController.class);

    @GetMapping
    public String processEchoMessage() {
        LOG.info("Tower Service is ready");
        System.out.println("Tower Service is ready");
        return "Tower Service is ready";
    }

    @GetMapping("/bootstrap")
    public String processBootstrapMessage() {
        LOG.info("Tower Service has bootstrapped");
        System.out.println("Tower Service has bootstrapped");
        return "Tower Service has bootstrapped";
    }

    @GetMapping("/submit")
    public String processSubmitpMessage() {
        LOG.info("Tower Service has submitted request");
        System.out.println("Tower Service has submitted request");
        return "Tower Service has submitted request";
    }
}