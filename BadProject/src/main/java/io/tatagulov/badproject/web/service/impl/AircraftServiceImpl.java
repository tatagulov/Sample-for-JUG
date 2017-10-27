package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.aircraft.AircraftDTO;
import io.tatagulov.badproject.web.dto.aircraft.AircraftListRequest;
import io.tatagulov.badproject.web.entity.AircraftsEntity;
import io.tatagulov.badproject.web.mapper.AircraftMapper;
import io.tatagulov.badproject.web.repo.AircraftRepository;
import io.tatagulov.badproject.web.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.tatagulov.badproject.web.util.Utils.validateNull;
import static java.util.stream.Collectors.toList;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftMapper aircraftMapper) {
        this.aircraftRepository = aircraftRepository;
        this.aircraftMapper = aircraftMapper;
    }

    @Override
    public void save(AircraftDTO aircraftDTO) {
        validate(aircraftDTO);
        AircraftsEntity aircraftEntity = aircraftRepository.findById(aircraftDTO.getAircraftCode());
        if (aircraftEntity == null) {
            aircraftEntity = new AircraftsEntity();
            aircraftEntity.setAircraftCode(aircraftDTO.getAircraftCode());
        }
        aircraftEntity.setModel(aircraftDTO.getModel());
        aircraftEntity.setRange(aircraftDTO.getRange());
        aircraftEntity.setDescription(aircraftDTO.getDescription());
        aircraftRepository.save(aircraftEntity);
    }

    private void validate(AircraftDTO aircraftDTO) {
        validateNull(aircraftDTO.getAircraftCode(),"aircraftCode");
        validateNull(aircraftDTO.getModel(),"model");
        validateNull(aircraftDTO.getRange(),"range");
    }

    @Override
    public void delete(String code) {
        AircraftsEntity aircraftEntity = aircraftRepository.findById(code);
        if (aircraftEntity!=null) {
            aircraftRepository.delete(aircraftEntity);
        }
    }

    @Override
    public List<AircraftDTO> getList(AircraftListRequest aircraftListRequest) {
        List<AircraftsEntity> list = aircraftRepository.getList(aircraftListRequest);
        return list.stream().map(aircraftMapper::entityToDTO).collect(toList());
    }

    @Override
    public AircraftDTO selectOne(String aircraftCode) {
        AircraftsEntity entity = aircraftRepository.findById(aircraftCode);
        return aircraftMapper.entityToDTO(entity);
    }
}
