package Student_Bus.Student_Busm.repository;

import Student_Bus.Student_Busm.entity.BusStopage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopageRepository extends JpaRepository<BusStopage, String> {
}
