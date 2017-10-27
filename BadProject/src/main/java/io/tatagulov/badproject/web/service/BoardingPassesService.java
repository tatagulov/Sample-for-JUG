package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListRequest;

import java.util.List;

public interface BoardingPassesService {
    List<BoardingPassesListDTO> getList(BoardingPassesListRequest request);

    void save(BoardingPassesDTO boardingPassesDTO);

    void delete(BoardingPassesDTO boardingPassesDTO);

    BoardingPassesDTO selectOne(BoardingPassesDTO boardingPassesDTO);
}
