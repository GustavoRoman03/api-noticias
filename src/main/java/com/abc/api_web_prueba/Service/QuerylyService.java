package com.abc.api_web_prueba.Service;

import com.abc.api_web_prueba.Dto.ResponseDTO;
import com.abc.api_web_prueba.Model.Noticias;
import com.abc.api_web_prueba.Dto.QuerylyResponse;
import com.abc.api_web_prueba.Service.IService.IQuerylyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuerylyService implements IQuerylyService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseDTO consultar(String query, Integer endIndex, Integer batchSize, Boolean showFaceted, String extendedDataFields) {
        try {
            // Validar parámetros obligatorios
            if (endIndex == null || batchSize == null) {
                return new ResponseDTO("Los parámetros 'indiceFinal' y 'cantidadElementos' son obligatorios.", HttpStatus.BAD_REQUEST);
            }

            if (showFaceted == null) {
                showFaceted = false;
            }

            String url = buildQuerylyUrl(query, endIndex, batchSize, showFaceted, extendedDataFields);
            QuerylyResponse queryResponse = restTemplate.getForObject(url, QuerylyResponse.class);
            Noticias noticias = mapToNoticias(queryResponse, showFaceted);
            return new ResponseDTO(new Date(), HttpStatus.OK, "Noticias obtenidas exitosamente", noticias);
        } catch (Exception e) {
            return new ResponseDTO("Error desconocido al obtener noticias", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private String buildQuerylyUrl(String query, Integer endIndex, Integer batchSize, Boolean showFaceted, String extendedDataFields) {
        StringBuilder urlBuilder = new StringBuilder("https://api.queryly.com/json.aspx");
        urlBuilder.append("?queryly_key=33530b56c6aa4c20");
        urlBuilder.append("&query=").append(query);
        if (endIndex != null) {
            urlBuilder.append("&endindex=").append(endIndex);
        }
        if (batchSize != null) {
            urlBuilder.append("&batchsize=").append(batchSize);
        }
        if (showFaceted != null) {
            urlBuilder.append("&showfaceted=").append(showFaceted);
        }
        if (extendedDataFields != null) {
            urlBuilder.append("&extendeddatafields=").append(extendedDataFields);
        }
        return urlBuilder.toString();
    }

    private Noticias mapToNoticias(QuerylyResponse queryResponse, Boolean showFaceted) {
        Noticias noticias = new Noticias();

        // Metadatos
        Noticias.Metadatos metadatos = new Noticias.Metadatos();
        metadatos.setConsulta(queryResponse.getMetadata().getQuery());
        metadatos.setTotal(queryResponse.getMetadata().getTotal());
        metadatos.setIndiceFinal(queryResponse.getMetadata().getEndIndex());
        metadatos.setCorreccion(queryResponse.getMetadata().getCorrection());
        metadatos.setFiltros(queryResponse.getMetadata().getFilters());
        noticias.setMetadatos(metadatos);

        // Facetas
        if (showFaceted) {
            Noticias.Facetas facetas = new Noticias.Facetas();
            facetas.setSeccion(queryResponse.getFaceted().getSection().stream()
                    .map(s -> new Noticias.Seccion(s.getKey(), s.getValue()))
                    .collect(Collectors.toList()));
            facetas.setFechaPublicacion(queryResponse.getFaceted().getPubDate().stream()
                    .map(p -> new Noticias.FechaPublicacion(p.getKey(), p.getValue()))
                    .collect(Collectors.toList()));
            facetas.setEtiquetas(queryResponse.getFaceted().getTag().stream()
                    .map(t -> new Noticias.Etiqueta(t.getKey(), t.getValue()))
                    .collect(Collectors.toList()));
            noticias.setFacetas(facetas);
        } else {
            noticias.setFacetas(null);
        }

        // Artículos
        List<Noticias.Articulo> articulos = queryResponse.getItems().stream()
                .map(item -> new Noticias.Articulo(
                        item.get_id(),
                        item.getIndex(),
                        item.getTitle(),
                        item.getLink(),
                        item.getDescription(),
                        item.getPubdate(),
                        item.getPubdateunix(),
                        item.getImage(),
                        item.get_type(),
                        item.getFeedname(),
                        item.getCreator(),
                        item.getImageresizer(),
                        item.getPromo_image()
                ))
                .collect(Collectors.toList());
        noticias.setArticulos(articulos);

        return noticias;
    }

    private List<Noticias.Seccion> mapSecciones(List<QuerylyResponse.Section> sections) {
        if (sections == null) {
            return Collections.emptyList();
        }
        return sections.stream()
                .map(section -> new Noticias.Seccion(section.getKey(), section.getValue()))
                .collect(Collectors.toList());
    }

    private List<Noticias.FechaPublicacion> mapFechasPublicacion(List<QuerylyResponse.PubDate> pubDates) {
        if (pubDates == null) {
            return Collections.emptyList();
        }
        return pubDates.stream()
                .map(pubDate -> new Noticias.FechaPublicacion(pubDate.getKey(), pubDate.getValue()))
                .collect(Collectors.toList());
    }

    private List<Noticias.Etiqueta> mapEtiquetas(List<QuerylyResponse.Tag> tags) {
        if (tags == null) {
            return Collections.emptyList();
        }
        return tags.stream()
                .map(tag -> new Noticias.Etiqueta(tag.getKey(), tag.getValue()))
                .collect(Collectors.toList());
    }
}