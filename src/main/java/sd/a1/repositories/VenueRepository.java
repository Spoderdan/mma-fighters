package sd.a1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.a1.models.Venue;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
