package com.abc.api_web_prueba.Service.IService;

import com.abc.api_web_prueba.Dto.ResponseDTO;

public interface IQuerylyService {
    ResponseDTO consultar(String query, Integer endIndex, Integer batchSize, Boolean showFaceted, String extendedDataFields);
}
