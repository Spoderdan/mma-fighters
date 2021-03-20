package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sd.a1.models.Venue;
import sd.a1.repositories.VenueRepository;

import java.util.List;

@RestController
@RequestMapping(path="/venue")
public class VenueController {

    private final VenueRepository venueRepository;

    @Autowired
    public VenueController(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @GetMapping(path = "/add")
    public String addNewVenue(@RequestParam Integer fighter1, @RequestParam Integer fighter2, @RequestParam Integer tournament){
        Venue venue = new Venue();
        venue.setFighter1(fighter1);
        venue.setFighter2(fighter2);
        venue.setTournament(tournament);
        venueRepository.save(venue);
        return "Saved new venue";
    }

    @GetMapping(path = "/list")
    public List<Venue> getAllVenues(){
        return venueRepository.findAll();
    }

}
