package com.icepoint.sample.application.service.mapper;

import com.icepoint.sample.application.domain.*;
import com.icepoint.sample.application.service.dto.AssetsLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AssetsLog and its DTO AssetsLogDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface AssetsLogMapper extends EntityMapper<AssetsLogDTO, AssetsLog> {

    @Mapping(source = "employee.id", target = "employeeId")
    AssetsLogDTO toDto(AssetsLog assetsLog);

    @Mapping(source = "employeeId", target = "employee")
    AssetsLog toEntity(AssetsLogDTO assetsLogDTO);

    default AssetsLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        AssetsLog assetsLog = new AssetsLog();
        assetsLog.setId(id);
        return assetsLog;
    }
}
