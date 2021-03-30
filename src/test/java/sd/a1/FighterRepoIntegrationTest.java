package sd.a1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sd.a1.models.Fighter;
import sd.a1.repositories.FighterRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = A1Application.class)
@WebAppConfiguration
public class FighterRepoIntegrationTest {

    @Autowired
    private FighterRepository fighterRepository;

    @Test
    public void testFindAll() {
        List<Fighter> fighters = fighterRepository.findAll();
        assertTrue(fighters.size() > 0);
    }

    @Test
    public void testFindId() {
        Fighter fighter = fighterRepository.findFighterById(1);
        assertEquals(fighter.getId(), Integer.valueOf(1));
    }

    @Test
    public void testFindName() {
        Fighter fighter = fighterRepository.findFighterByFirstNameAndLastName("Homer", "Simpson");
        assertEquals(fighter.getFirstName(), "Homer");
        assertEquals(fighter.getLastName(), "Simpson");
    }

}
