package sd.a1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sd.a1.models.TestResult;
import sd.a1.repositories.TestResultsRepository;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = A1Application.class)
@WebAppConfiguration
public class TestResultsRepoIntegrationTest {

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Test
    public void testFindAll() {
        List<TestResult> testResults = testResultsRepository.findAll();
        assertTrue(testResults.size() > 0);
    }

}