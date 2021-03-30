package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sd.a1.models.Fighter;
import sd.a1.models.TestResult;

import java.sql.Date;
import java.util.List;

public interface TestResultsRepository extends JpaRepository<TestResult, Integer> {

    List<TestResult> findByFighterId(Fighter fighterId);

    @Query(value = "select max(t.date) from TestResult t where t.fighterId = ?1")
    Date findMaxDate(Fighter fighterId);

}
