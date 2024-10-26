package um.prog2.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import um.prog2.domain.Opcion;
import java.util.List;

/**
 * Spring Data JPA repository for the Opcion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcionRepository extends JpaRepository<Opcion, Long> {

    // Método personalizado para obtener todas las opciones por el ID de la personalización
    List<Opcion> findAllByPersonalizacionId(Long personalizacionId);
}
