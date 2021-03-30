package sd.a1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sd.a1.models.Tournament;
import sd.a1.repositories.TournamentRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = A1Application.class)
@WebAppConfiguration
public class TournamentRepoIntegrationTest {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Test
    public void testFindAll() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        assertTrue(tournaments.size() > 0);
    }

    @Test
    public void testFindId() {
        Tournament tournament = tournamentRepository.findTournamentById(1);
        assertEquals(tournament.getId(), Integer.valueOf(1));
    }

}