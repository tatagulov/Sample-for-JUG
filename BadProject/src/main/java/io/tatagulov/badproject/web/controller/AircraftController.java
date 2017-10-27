package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.aircraft.AircraftDTO;
import io.tatagulov.badproject.web.dto.aircraft.AircraftListRequest;
import io.tatagulov.badproject.web.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/aircraft")
@Transactional
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @RequestMapping("/selectOne")
    public AircraftDTO selectOne(String aircraftCode) {
        return aircraftService.selectOne(aircraftCode);
    }

    @RequestMapping("/save")
    public void save(@RequestBody AircraftDTO aircraftDTO) {
        aircraftService.save(aircraftDTO);
    }

    @RequestMapping("delete")
    public void delete(@RequestParam String aircraftCode) {
        aircraftService.delete(aircraftCode);
    }

    @RequestMapping("/list")
    public List<AircraftDTO> list(@RequestBody AircraftListRequest aircraftListRequest) {
        return aircraftService.getList(aircraftListRequest);
    }
}
