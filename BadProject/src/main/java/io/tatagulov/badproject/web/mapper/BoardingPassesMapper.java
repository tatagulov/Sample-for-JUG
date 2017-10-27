package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListDTO;
import io.tatagulov.badproject.web.entity.BoardingPassesEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import org.springframework.stereotype.Service;

@Service
public class BoardingPassesMapper {
    public BoardingPassesListDTO entityToListDTO(BoardingPassesEntity entity) {
        TicketFlightsEntity ticketFlights = entity.getTicketFlights();

        BoardingPassesListDTO dto = new BoardingPassesListDTO();

        dto.setFlightId(entity.getFlightId());
        dto.setTicketNo(entity.getTicketNo());
        dto.setPassengerName(ticketFlights.getTicketsByTicketNo().getPassengerName());
        dto.setFlightNo(ticketFlights.getFlightsByFlightId().getFlightNo());
        dto.setSeatNo(entity.getSeatNo());
        return dto;
    }

    public BoardingPassesDTO entityToDTO(BoardingPassesEntity entity) {
        BoardingPassesDTO dto = new BoardingPassesDTO();
        dto.setFlightId(entity.getFlightId());
        dto.setTicketNo(entity.getTicketNo());
        dto.setSeatNo(entity.getSeatNo());
        dto.setBoardingNo(entity.getBoardingNo());
        return dto;
    }
}
