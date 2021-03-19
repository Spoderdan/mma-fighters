package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.a1.models.Fighter;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Integer> {

    Fighter findFighterById(Integer id);

    Iterable<Fighter> findFighterByFirstName(String firstName);

}
