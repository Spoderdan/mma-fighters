package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sd.a1.models.Venue;
import sd.a1.repositories.VenueRepository;

@Controller
@RequestMapping(path="/venue")
public class VenueController {

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewVenue(@RequestParam Integer fighter, @RequestParam Integer tournament){
        Venue venue = new Venue();
        venue.setFighter(fighter);
        venue.setTournament(tournament);
        venueRepository.save(venue);
        return "Saved new venue";
    }

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Venue> getAllVenues(){
        return venueRepository.findAll();
    }

}
