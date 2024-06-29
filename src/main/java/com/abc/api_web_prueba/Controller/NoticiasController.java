package com.abc.api_web_prueba.Controller;

import com.abc.api_web_prueba.Dto.ResponseDTO;
import com.abc.api_web_prueba.Service.IService.IQuerylyService;
import com.abc.api_web_prueba.Service.QuerylyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/noticias")
public class NoticiasController {

    private final IQuerylyService querylyService;

    @Autowired
    public NoticiasController(QuerylyService querylyService) {
        this.querylyService = querylyService;
    }
@GetMapping(value = "/obtener", produces = "application/json")
public ResponseEntity<ResponseDTO> obtenerNoticias(@RequestParam String consulta,
                                                   @RequestParam(required = false) Integer indiceFinal,
                                                   @RequestParam(required = false) Integer cantidadElementos,
                                                   @RequestParam(required = false) Boolean mostrarCategorias,
                                                   @RequestParam(required = false) String mostrarArchivosAdicionales) {
    try {
        // Llamar al servicio para obtener las noticias mapeadas correctamente
        return querylyService.consultar(consulta, indiceFinal, cantidadElementos, mostrarCategorias, mostrarArchivosAdicionales).build();
    } catch (Exception e) {
        // Manejo genérico de errores
        return new ResponseEntity<>(new ResponseDTO("Error al obtener noticias: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}