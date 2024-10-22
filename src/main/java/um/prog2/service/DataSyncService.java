package um.prog2.service;

import static um.prog2.service.DataFetcherService.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import um.prog2.domain.Adicional;
import um.prog2.domain.Caracteristica;
import um.prog2.domain.Dispositivo;
import um.prog2.domain.Opcion;
import um.prog2.domain.Personalizacion;
import um.prog2.repository.AdicionalRepository;
import um.prog2.repository.CaracteristicaRepository;
import um.prog2.repository.DispositivoRepository;
import um.prog2.repository.OpcionRepository;
import um.prog2.repository.PersonalizacionRepository;

@Service
@Transactional
public class DataSyncService {

    private final Logger log = LoggerFactory.getLogger(DataSyncService.class);

    private final DispositivoRepository dispositivoRepository;
    private final CaracteristicaRepository caracteristicaRepository;
    private final PersonalizacionRepository personalizacionRepository;
    private final OpcionRepository opcionRepository;
    private final AdicionalRepository adicionalRepository;

    private final DataFetcherService dataFetcherService;

    private final ObjectMapper mapper = new ObjectMapper();

    public DataSyncService(
        DispositivoRepository dispositivoRepository,
        CaracteristicaRepository caracteristicaRepository,
        PersonalizacionRepository personalizacionRepository,
        OpcionRepository opcionRepository,
        AdicionalRepository adicionalRepository,
        DataFetcherService dataFetcherService
    ) {
        this.dispositivoRepository = dispositivoRepository;
        this.caracteristicaRepository = caracteristicaRepository;
        this.personalizacionRepository = personalizacionRepository;
        this.opcionRepository = opcionRepository;
        this.adicionalRepository = adicionalRepository;
        this.dataFetcherService = dataFetcherService;
    }

    // Método que se ejecuta al iniciar la aplicación
    @PostConstruct
    public void init() {
        log.info("Iniciando sincronización de datos al iniciar la aplicación...");
        syncData();
    }

    // Ejecutar cada 6 horas
    @Scheduled(fixedRate = 21600000)
    public void syncData() {
        log.info("Iniciando sincronización de datos programada...");
        try {
            syncDispositivos();
            syncCaracteristicas();
            syncPersonalizaciones();
            syncOpciones();
            syncAdicionales();
            log.info("Sincronización de datos completada.");
        } catch (Exception e) {
            log.error("Error en la sincronización de datos: ", e);
        }
    }

    private void syncDispositivos() throws IOException {
        // Llamamos al método de instancia getDispositivos() correctamente
        List<Dispositivo> dispositivos = parseDispositivos();
        for (Dispositivo dispositivo : dispositivos) {
            if (!dispositivoRepository.existsById(dispositivo.getId())) {
                dispositivoRepository.save(dispositivo);
            }
        }
    }

    private void syncCaracteristicas() throws IOException {
        List<Caracteristica> caracteristicas = parseCaracteristicas();
        for (Caracteristica caracteristica : caracteristicas) {
            if (!caracteristicaRepository.existsById(caracteristica.getId())) {
                caracteristicaRepository.save(caracteristica);
            }
        }
    }

    private void syncPersonalizaciones() throws IOException {
        List<Personalizacion> personalizaciones = parsePersonalizaciones();
        for (Personalizacion personalizacion : personalizaciones) {
            if (!personalizacionRepository.existsById(personalizacion.getId())) {
                personalizacionRepository.save(personalizacion);
            }
        }
    }

    private void syncOpciones() throws IOException {
        List<Opcion> opciones = parseOpciones();
        for (Opcion opcion : opciones) {
            if (!opcionRepository.existsById(opcion.getId())) {
                opcionRepository.save(opcion);
            }
        }
    }

    private void syncAdicionales() throws IOException {
        List<Adicional> adicionales = parseAdicionales();
        for (Adicional adicional : adicionales) {
            if (!adicionalRepository.existsById(adicional.getId())) {
                adicionalRepository.save(adicional);
            }
        }
    }

    // Métodos de parseo
    private List<Dispositivo> parseDispositivos() throws IOException {
        // Acceder al método como instancia, no estático
        String jsonData = DataFetcherService.getDispositivos();
        return mapper.readValue(jsonData, new TypeReference<List<Dispositivo>>() {});
    }

    private List<Caracteristica> parseCaracteristicas() throws IOException {
        String jsonData = dataFetcherService.getCaracteristicas();
        return mapper.readValue(jsonData, new TypeReference<List<Caracteristica>>() {});
    }

    private List<Personalizacion> parsePersonalizaciones() throws IOException {
        String jsonData = dataFetcherService.getPersonalizaciones();
        return mapper.readValue(jsonData, new TypeReference<List<Personalizacion>>() {});
    }

    private List<Opcion> parseOpciones() throws IOException {
        String jsonData = dataFetcherService.getOpciones();
        return mapper.readValue(jsonData, new TypeReference<List<Opcion>>() {});
    }

    private List<Adicional> parseAdicionales() throws IOException {
        String jsonData = dataFetcherService.getAdicionales();
        return mapper.readValue(jsonData, new TypeReference<List<Adicional>>() {});
    }
}
