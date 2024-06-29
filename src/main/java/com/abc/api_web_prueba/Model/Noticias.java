package com.abc.api_web_prueba.Model;
import com.abc.api_web_prueba.Dto.QuerylyResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticias {
    private Metadatos metadatos;
    private Facetas facetas;
    private List<Articulo> articulos;

    @Data
    public static class Metadatos {
        private String consulta;
        private int total;
        private int indiceFinal;
        private String correccion;
        private List<String> filtros;

        public Metadatos(String query, int total, int endIndex, String correction, List<String> filters) {
        }

        public Metadatos() {

        }
    }

    @Data
    public static class Facetas {
        private List<Seccion> seccion;
        private List<FechaPublicacion> fechaPublicacion;
        private List<Etiqueta> etiquetas;

        public Facetas(List<Seccion> mapSecciones, List<FechaPublicacion> mapFechasPublicacion, List<Etiqueta> mapEtiquetas) {
        }

        public Facetas() {

        }
    }

    @Data
    public static class Seccion {
        private String clave;
        private int valor;

        public Seccion(String clave, int valor) {
            this.clave = clave != null ? clave : "";
            this.valor = valor;
        }
    }

    @Data
    public static class FechaPublicacion {
        private String clave;
        private int valor;

        public FechaPublicacion(String clave, int valor) {
            this.clave = clave != null ? clave : "";
            this.valor = valor;
        }
    }

    @Data
    public static class Etiqueta {
        private String clave;
        private int valor;

        public Etiqueta(String clave, int valor) {
            this.clave = clave != null ? clave : "";
            this.valor = valor;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Articulo {
        private int id;
        private int indice;
        private String titulo;
        private String enlace;
        private String descripcion;
        private String fechaPublicacion;
        private String fechaPublicacionUnix;
        private String imagen;
        private int tipo;
        private String fuente;
        private String creador;
        private String redimencionarImagen;
        private String imagenPromocional;

        public static Articulo fromQuerylyItem(QuerylyResponse.Item item) {
            return new Articulo(
                    item.get_id(),
                    item.getIndex(),
                    item.getTitle(),
                    item.getLink(),
                    item.getDescription(),
                    item.getPubdate(),
                    String.valueOf(item.getPubdateunix()),
                    item.getImage(),
                    item.get_type(),
                    item.getFeedname(),
                    item.getCreator(),
                    item.getImageresizer(),
                    item.getPromo_image()
            );
        }
    }
}
