package sd.a1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sd.a1.models.Venue;
import sd.a1.repositories.VenueRepository;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = A1Application.class)
@WebAppConfiguration
public class VenueRepoIntegrationTest {

    @Autowired
    private VenueRepository venueRepository;

    @Test
    public void testFindAll() {
        List<Venue> venues = venueRepository.findAll();
        assertTrue(venues.size() > 0);
    }

}