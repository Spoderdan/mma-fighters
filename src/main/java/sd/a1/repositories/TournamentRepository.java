package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.a1.models.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

}
