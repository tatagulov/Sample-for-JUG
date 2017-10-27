package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.ticket.TicketDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListRequest;
import io.tatagulov.badproject.web.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("ticket")
@Transactional
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping("/selectOne")
    public TicketDTO selectOne(@RequestParam String ticketNo) {
        return ticketService.selectOne(ticketNo);
    }

    @RequestMapping("/save")
    public void save(@RequestBody TicketDTO ticketDTO) {
        ticketService.save(ticketDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam String ticketNo) {
        ticketService.delete(ticketNo);
    }

    @RequestMapping("/list")
    public List<TicketListDTO> list(@RequestBody TicketListRequest ticketListRequest) {
        return ticketService.getList(ticketListRequest);
    }
}