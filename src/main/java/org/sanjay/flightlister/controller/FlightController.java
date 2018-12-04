package org.sanjay.flightlister.controller;

import org.sanjay.flightlister.model.Flight;
import org.sanjay.flightlister.model.FlightRequest;
import org.sanjay.flightlister.repository.FlightRepository;
import org.sanjay.flightlister.service.FlightSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightSearchService flightSearchService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @PostMapping(value = "/api/flight", produces = "application/json")
    public List<Flight> getRoute(@Valid @RequestBody FlightRequest flightRequest) throws ExecutionException, InterruptedException {

        logger.info("Received data for flight-lister. Invoking Camel route");
        List<Flight> flights = flightSearchService.findFlights(flightRequest);
        logger.info("Completed flight-lister request successfully");

        return flights;
    }
}
