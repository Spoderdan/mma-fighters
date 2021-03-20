package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.a1.models.Fighter;
import sd.a1.models.TestResult;

import java.util.List;

public interface TestResultsRepository extends JpaRepository<TestResult, Integer> {

    List<TestResult> findByFighterId(Fighter fighterId);

}
