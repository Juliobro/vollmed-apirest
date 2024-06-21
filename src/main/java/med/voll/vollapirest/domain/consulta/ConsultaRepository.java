package med.voll.vollapirest.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPaciente_IdAndFechaBetween(Long id, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
    boolean existsByMedico_IdAndFecha(Long id, LocalDateTime fecha);
}
