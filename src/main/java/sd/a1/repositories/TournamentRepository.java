package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.a1.models.Tournament;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    Tournament findTournamentById(Integer id);

    List<Tournament> findTournamentByFilled(String filled);
}
