package com.sb.tower.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TowerServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(TowerServiceImpl.class);

    //This is used to count how many towers have been created
    private final Counter towerCreatedCounter;

    //This measures the time taken to create each tower, allowing for performance monitoring and optimization
    private final Timer towerCreationTimer;

    //A Gauge (backed by activeTowerCreationRequests) tracks the number of active requests at any given time,
    // providing real-time insight into load and concurrency levels.
    private final AtomicInteger activeTowerCreationRequests;

    public TowerServiceImpl(MeterRegistry registry) {
        towerCreatedCounter = Counter.builder("towers.created")
                .description("Number of towers created")
                .register(registry);

        towerCreationTimer = Timer.builder("towers.creation.time")
                .description("Time taken to create a tower")
                .register(registry);

        activeTowerCreationRequests = new AtomicInteger(0);
        Gauge.builder("towers.creation.active.requests", activeTowerCreationRequests,AtomicInteger::get)
                .description("Active tower creation requests")
                .register(registry);
    }

    public void submitTower() {
        activeTowerCreationRequests.incrementAndGet();
        try {
            //Simulate tower creation time
            towerCreationTimer.record(() -> {
                //Perform the tower creation / setup action
                LOG.info("Tower request id created: " + UUID.randomUUID());
//                LOG.info("Tower subsidiary request status: " + checkSubsidiaryAndCreateDefault());
            });
        } finally {
            towerCreatedCounter.increment();
            activeTowerCreationRequests.decrementAndGet();
            LOG.info("Tower request submitted");
        }
    }

//    public boolean checkSubsidiaryAndCreateDefault()
//    {
//        String host = "http://localhost:8080";
//        String apiEndpoint = "/subsidiary/submit";
//        //Setup the API call
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        LOG.info("Tower Subsidiary API being used @ " + host + apiEndpoint);
//
//        //Execute the API call and return its availability status
//        return new RestTemplate().exchange(host + apiEndpoint, HttpMethod.GET, entity, String.class).getStatusCode().is2xxSuccessful();
//    }

}