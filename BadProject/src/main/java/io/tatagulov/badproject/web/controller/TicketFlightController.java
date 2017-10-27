package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightDTO;
import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightListRequest;
import io.tatagulov.badproject.web.service.TicketFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("ticketFlight")
@Transactional
public class TicketFlightController {

    private final TicketFlightService ticketFlightService;

    @Autowired
    public TicketFlightController(TicketFlightService ticketFlightService) {
        this.ticketFlightService = ticketFlightService;
    }

    @RequestMapping("/selectOne")
    public TicketFlightDTO selectOne(@RequestParam Integer flightId,
                                     @RequestParam String ticketNo) {
        return ticketFlightService.selectOne(flightId,ticketNo);
    }

    @RequestMapping("/save")
    public void save(@RequestBody TicketFlightDTO ticketFlightDTO) {
        ticketFlightService.save(ticketFlightDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam Integer flightId,
                       @RequestParam String ticketNo) {
        ticketFlightService.delete(flightId,ticketNo);
    }

    @RequestMapping("/list")
    public List<TicketFlightDTO> list(@RequestBody TicketFlightListRequest ticketFlightListRequest) {
        return ticketFlightService.getList(ticketFlightListRequest);
    }
}
