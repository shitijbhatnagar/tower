package com.sb.tower.web;

import com.sb.tower.service.TowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/tower")
public class TowerController {

    @Autowired
    TowerServiceImpl towerService;

    public TowerController(TowerServiceImpl towerService)
    {
        this.towerService = towerService;
    }

    private static final Logger LOG = LoggerFactory.getLogger(TowerController.class);

    @GetMapping
    public String processEchoMessage() {
        LOG.info("Tower Service is ready");
        return "Tower Service is ready";
    }

    @GetMapping("/bootstrap")
    public String processBootstrapMessage() {
        LOG.info("Tower Service has bootstrapped");
        return "Tower Service has bootstrapped";
    }

    @GetMapping("/submit")
    public String processSubmitpMessage() {
        towerService.submitTower();
        LOG.info("Tower Service has submitted request");
        return "Tower Service has submitted request";
    }
}