package org.sanjay.flightlister.repository;

import org.sanjay.flightlister.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public interface FlightRepository extends CrudRepository<Flight, Long> {
    @Async
    @Query("SELECT f from Flight f WHERE f.departure = :departure AND f.destination = :destination AND f.departureDate = :departureDate")
    Future<List<Flight>> findFlights(@Param("departure") String departure, @Param("destination") String destination, @Param("departureDate")Date departureDate);
}
