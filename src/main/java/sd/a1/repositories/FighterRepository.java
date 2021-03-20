package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.a1.models.Fighter;

import java.util.List;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Integer> {

    Fighter findFighterById(Integer id);

    List<Fighter> findFighterByFirstName(String firstName);

}
