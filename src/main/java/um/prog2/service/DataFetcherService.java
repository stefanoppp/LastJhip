package um.prog2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataFetcherService {

    private static final String URL = "http://192.168.194.254:8080/api/catedra/dispositivos";
    private static final String JWT_TOKEN =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdGVmYW5vMTIzIiwiZXhwIjoxNzM3NzUxMjYzLCJhdXRoIjoiUk9MRV9VU0VSIiwiaWF0IjoxNzI5MTExMjYzfQ.MN7mDl7nswUM30IHJlvkfSjjqe_5NAEC6CGIF07SHCu-1R2fr1cETw0MtX7G6Aa47WqiPsoY5qPTgR2REs_jFA";
    public static ObjectMapper mapper = new ObjectMapper();

    private static String getDataFromApi() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);
            request.addHeader("Authorization", "Bearer " + JWT_TOKEN);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
    }

    public static String getDispositivos() throws IOException {
        String jsonResponse = getDataFromApi();
        ArrayNode originalArray = (ArrayNode) mapper.readTree(jsonResponse);
        ArrayNode resultArray = mapper.createArrayNode();
        Set<Integer> processedIds = new HashSet<>();

        for (int i = 0; i < originalArray.size(); i++) {
            ObjectNode device = (ObjectNode) originalArray.get(i);
            int id = device.get("id").asInt();

            if (!processedIds.contains(id)) {
                processedIds.add(id);
                ObjectNode simpleDevice = mapper.createObjectNode();
                simpleDevice.put("id", id);
                simpleDevice.put("codigo", device.get("codigo").asText());
                simpleDevice.put("nombre", device.get("nombre").asText());
                simpleDevice.put("descripcion", device.get("descripcion").asText());
                simpleDevice.put("precioBase", device.get("precioBase").asDouble());
                simpleDevice.put("moneda", device.get("moneda").asText());
                resultArray.add(simpleDevice);
            }
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultArray);
    }

    public static String getCaracteristicas() throws IOException {
        String jsonResponse = getDataFromApi();
        ArrayNode originalArray = (ArrayNode) mapper.readTree(jsonResponse);
        ArrayNode resultArray = mapper.createArrayNode();
        Set<Integer> processedIds = new HashSet<>();

        for (int i = 0; i < originalArray.size(); i++) {
            ObjectNode device = (ObjectNode) originalArray.get(i);
            ArrayNode caracteristicas = (ArrayNode) device.get("caracteristicas");

            for (int j = 0; j < caracteristicas.size(); j++) {
                ObjectNode caracteristica = (ObjectNode) caracteristicas.get(j);
                int caracteristicaId = caracteristica.get("id").asInt();

                if (!processedIds.contains(caracteristicaId)) {
                    processedIds.add(caracteristicaId);
                    // Aquí añadimos el dispositivo anidado
                    ObjectNode dispositivo = mapper.createObjectNode();
                    dispositivo.put("id", device.get("id").asInt());
                    dispositivo.put("nombre", device.get("nombre").asText());
                    caracteristica.set("dispositivo", dispositivo);
                    resultArray.add(caracteristica);
                }
            }
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultArray);
    }

    public static String getPersonalizaciones() throws IOException {
        String jsonResponse = getDataFromApi();
        ArrayNode originalArray = (ArrayNode) mapper.readTree(jsonResponse);
        ArrayNode resultArray = mapper.createArrayNode();
        Set<Integer> processedIds = new HashSet<>();

        for (int i = 0; i < originalArray.size(); i++) {
            ObjectNode device = (ObjectNode) originalArray.get(i);
            ArrayNode personalizaciones = (ArrayNode) device.get("personalizaciones");

            for (int j = 0; j < personalizaciones.size(); j++) {
                ObjectNode personalizacion = (ObjectNode) personalizaciones.get(j);
                int personalizacionId = personalizacion.get("id").asInt();

                if (!processedIds.contains(personalizacionId)) {
                    processedIds.add(personalizacionId);
                    // Anidar dispositivo en personalización
                    ObjectNode dispositivo = mapper.createObjectNode();
                    dispositivo.put("id", device.get("id").asInt());
                    dispositivo.put("nombre", device.get("nombre").asText());
                    personalizacion.set("dispositivo", dispositivo);
                    personalizacion.remove("opciones");
                    resultArray.add(personalizacion);
                }
            }
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultArray);
    }

    public static String getOpciones() throws IOException {
        String jsonResponse = getDataFromApi();
        ArrayNode originalArray = (ArrayNode) mapper.readTree(jsonResponse);
        ArrayNode resultArray = mapper.createArrayNode();
        Set<Integer> processedIds = new HashSet<>();

        for (int i = 0; i < originalArray.size(); i++) {
            ObjectNode device = (ObjectNode) originalArray.get(i);
            ArrayNode personalizaciones = (ArrayNode) device.get("personalizaciones");

            for (int j = 0; j < personalizaciones.size(); j++) {
                ObjectNode personalizacion = (ObjectNode) personalizaciones.get(j);
                ArrayNode opciones = (ArrayNode) personalizacion.get("opciones");

                for (int k = 0; k < opciones.size(); k++) {
                    ObjectNode opcion = (ObjectNode) opciones.get(k);
                    int opcionId = opcion.get("id").asInt();

                    if (!processedIds.contains(opcionId)) {
                        processedIds.add(opcionId);
                        // Anidar personalización en opción
                        ObjectNode personalizacionNode = mapper.createObjectNode();
                        personalizacionNode.put("id", personalizacion.get("id").asInt());
                        personalizacionNode.put("nombre", personalizacion.get("nombre").asText());
                        opcion.set("personalizacion", personalizacionNode);
                        resultArray.add(opcion);
                    }
                }
            }
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultArray);
    }

    public static String getAdicionales() throws IOException {
        String jsonResponse = getDataFromApi();
        ArrayNode originalArray = (ArrayNode) mapper.readTree(jsonResponse);
        ArrayNode resultArray = mapper.createArrayNode();
        Set<Integer> processedIds = new HashSet<>();

        for (int i = 0; i < originalArray.size(); i++) {
            ObjectNode device = (ObjectNode) originalArray.get(i);
            ArrayNode adicionales = (ArrayNode) device.get("adicionales");

            for (int j = 0; j < adicionales.size(); j++) {
                ObjectNode adicional = (ObjectNode) adicionales.get(j);
                int adicionalId = adicional.get("id").asInt();

                if (!processedIds.contains(adicionalId)) {
                    processedIds.add(adicionalId);
                    // Anidar dispositivo en adicional
                    ObjectNode dispositivo = mapper.createObjectNode();
                    dispositivo.put("id", device.get("id").asInt());
                    dispositivo.put("nombre", device.get("nombre").asText());
                    adicional.set("dispositivo", dispositivo);
                    resultArray.add(adicional);
                }
            }
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultArray);
    }
}
