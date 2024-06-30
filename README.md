# api-noticias
Api para obtener noticias de abc color

se debe clonar la rama master

## Detalles de la Solución
1. **Lenguaje de Programación**:
   - Java 17 para la implementación del API RESTful.

El API implementa la consulta:
http://localhost:8080/api/noticias/obtener?consulta=ElTextoDeLaConsulta
la misma requiere un token para funcionar, el mismo se obtiene de 
http://localhost:8080/auth/generate-token
Ambos son GET
La misma puede probarse a traves de POSTMAN
