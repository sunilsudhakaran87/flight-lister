package org.sanjay.flightlister.service;

import org.sanjay.flightlister.model.Flight;
import org.sanjay.flightlister.model.FlightRequest;
import org.sanjay.flightlister.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    private static final Logger logger = LoggerFactory.getLogger("org.sanjay.flightlister.service.FlightSearchService");

    public List<Flight> findFlights(FlightRequest flightRequest) throws ExecutionException, InterruptedException {
        logger.info("Got a request for flight search service");
        logger.info("Request date is " + flightRequest.getStartDate());
        List<Flight> matchingFlights = flightRepository.findFlights(flightRequest.getStartCity(), flightRequest.getEndCity(), flightRequest.getStartDate()).get();
        logger.info("Matching flights are: " + matchingFlights);
        return matchingFlights;
    }
}
