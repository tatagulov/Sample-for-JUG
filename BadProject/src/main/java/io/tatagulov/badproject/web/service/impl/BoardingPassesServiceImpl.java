package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListRequest;
import io.tatagulov.badproject.web.entity.BoardingPassesEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntityPK;
import io.tatagulov.badproject.web.mapper.BoardingPassesMapper;
import io.tatagulov.badproject.web.repo.BoardingPassesRepository;
import io.tatagulov.badproject.web.service.BoardingPassesService;
import io.tatagulov.badproject.web.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BoardingPassesServiceImpl implements BoardingPassesService {

    private final BoardingPassesRepository boardingPassesRepository;
    private final BoardingPassesMapper mapper;

    @Autowired
    public BoardingPassesServiceImpl(BoardingPassesRepository boardingPassesRepository, BoardingPassesMapper mapper) {
        this.boardingPassesRepository = boardingPassesRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BoardingPassesListDTO> getList(BoardingPassesListRequest request) {
        List<BoardingPassesEntity> list = boardingPassesRepository.list(request);
        return list.stream().map(mapper::entityToListDTO).collect(toList());
    }

    @Override
    public void save(BoardingPassesDTO boardingPassesDTO) {
        validate(boardingPassesDTO);
        TicketFlightsEntityPK ticketFlightsEntityPK = new TicketFlightsEntityPK();
        ticketFlightsEntityPK.setTicketNo(boardingPassesDTO.getTicketNo());
        ticketFlightsEntityPK.setFlightId(boardingPassesDTO.getFlightId());

        BoardingPassesEntity entity = boardingPassesRepository.findById(ticketFlightsEntityPK);
        if (entity==null) {
            entity = new BoardingPassesEntity();
            entity.setFlightId(boardingPassesDTO.getFlightId());
            entity.setTicketNo(boardingPassesDTO.getTicketNo());
        }
        entity.setSeatNo(boardingPassesDTO.getSeatNo());
        entity.setBoardingNo(boardingPassesDTO.getBoardingNo());
        boardingPassesRepository.save(entity);
    }

    private void validate(BoardingPassesDTO boardingPassesDTO) {
        Utils.validateNull(boardingPassesDTO.getBoardingNo(),"BoardingNo");
        Utils.validateNull(boardingPassesDTO.getFlightId(),"FlightId");
        Utils.validateNull(boardingPassesDTO.getSeatNo(),"SeatNo");
        Utils.validateNull(boardingPassesDTO.getTicketNo(),"TicketNo");
    }

    @Override
    public void delete(BoardingPassesDTO boardingPassesDTO) {
        TicketFlightsEntityPK ticketFlightsEntityPK = new TicketFlightsEntityPK();
        ticketFlightsEntityPK.setTicketNo(boardingPassesDTO.getTicketNo());
        ticketFlightsEntityPK.setFlightId(boardingPassesDTO.getFlightId());

        BoardingPassesEntity entity = boardingPassesRepository.findById(ticketFlightsEntityPK);
        if (entity!=null) {
            boardingPassesRepository.delete(entity);
        }
    }

    @Override
    public BoardingPassesDTO selectOne(BoardingPassesDTO boardingPassesDTO) {
        TicketFlightsEntityPK ticketFlightsEntityPK = new TicketFlightsEntityPK();
        ticketFlightsEntityPK.setTicketNo(boardingPassesDTO.getTicketNo());
        ticketFlightsEntityPK.setFlightId(boardingPassesDTO.getFlightId());

        BoardingPassesEntity entity = boardingPassesRepository.findById(ticketFlightsEntityPK);
        return mapper.entityToDTO(entity);
    }
}
