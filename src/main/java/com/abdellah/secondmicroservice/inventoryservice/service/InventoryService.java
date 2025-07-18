package com.abdellah.secondmicroservice.inventoryservice.service;


import com.abdellah.secondmicroservice.inventoryservice.entity.Event;
import com.abdellah.secondmicroservice.inventoryservice.entity.Venue;
import com.abdellah.secondmicroservice.inventoryservice.repository.EventRepository;
import com.abdellah.secondmicroservice.inventoryservice.repository.VenueRepository;
import com.abdellah.secondmicroservice.inventoryservice.response.EventInventoryResponse;
import com.abdellah.secondmicroservice.inventoryservice.response.VenueInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }


    public List<EventInventoryResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().
                map(event -> EventInventoryResponse.builder().event(event.getName())
                        .venue(event.getVenue()).capacity(event.getTotalCapacity()).
                        ticketPrice(event.getTicketPrice()).build()).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInformation(Long venueId) {
        Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }
}
