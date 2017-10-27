package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListDTO;
import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListRequest;
import io.tatagulov.badproject.web.service.BoardingPassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/boarding")
@Transactional
public class BoardingPassesController {

    private final BoardingPassesService boardingPassesService;

    @Autowired
    public BoardingPassesController(BoardingPassesService boardingPassesService) {
        this.boardingPassesService = boardingPassesService;
    }

    @RequestMapping("/selectOne")
    public BoardingPassesDTO selectOne(@RequestBody BoardingPassesDTO boardingPassesDTO) {
        return boardingPassesService.selectOne(boardingPassesDTO);
    }

    @RequestMapping("/save")
    public void save(@RequestBody BoardingPassesDTO boardingPassesDTO) {
        boardingPassesService.save(boardingPassesDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody BoardingPassesDTO boardingPassesDTO) {
        boardingPassesService.delete(boardingPassesDTO);
    }

    @RequestMapping("/list")
    public List<BoardingPassesListDTO> list(@RequestBody BoardingPassesListRequest airportListRequest) {
        return boardingPassesService.getList(airportListRequest);
    }
}
