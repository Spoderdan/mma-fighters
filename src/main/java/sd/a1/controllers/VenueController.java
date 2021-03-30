package sd.a1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sd.a1.models.Fighter;
import sd.a1.models.TestResult;
import sd.a1.models.Tournament;
import sd.a1.models.VenueBuilder;
import sd.a1.repositories.FighterRepository;
import sd.a1.repositories.TestResultsRepository;
import sd.a1.repositories.TournamentRepository;
import sd.a1.repositories.VenueRepository;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(path="/venue")
public class VenueController {

    private final VenueRepository venueRepository;
    private final FighterRepository fighterRepository;
    private final TournamentRepository tournamentRepository;
    private final TestResultsRepository testResultsRepository;

    @Autowired
    public VenueController(VenueRepository venueRepository, FighterRepository fighterRepository, TournamentRepository tournamentRepository, TestResultsRepository testResultsRepository) {
        this.venueRepository = venueRepository;
        this.fighterRepository = fighterRepository;
        this.tournamentRepository = tournamentRepository;
        this.testResultsRepository = testResultsRepository;
    }


    @GetMapping(path = "/")
    public String getAllVenues(Model model){
        model.addAttribute("venues", venueRepository.findAll());
        model.addAttribute("fighters", fighterRepository.findAll());
        model.addAttribute("tournaments", tournamentRepository.findAll());
        return "venues";
    }

//    @PostMapping(path="/add")
//    public String addNewVenue(@RequestParam Integer fighter1, @RequestParam Integer fighter2, @RequestParam Integer match){
//        VenueBuilder venueBuilder = new VenueBuilder();
//        venueBuilder.addFighter1(fighter1);
//        venueBuilder.addFighter2(fighter2);
//        venueBuilder.addMatch(match);
//        venueRepository.save(venueBuilder.getVenue());
//        return "redirect:/venue/";
//    }

    @PostMapping(path="/time")
    public String passTime(){
        List<Fighter> unhealthyFighters = fighterRepository.findFighterByQuarantined("Yes");
        for(Fighter fighter : unhealthyFighters){
            boolean healthy = hasThreeNegativeTests(fighter);
            if(!healthy){
                TestResult testResult = new TestResult();
                testResult.setFighterId(fighter);
                testResult.setStatus(fighter.test());
                Date date = testResultsRepository.findMaxDate(fighter);
                long time = 1616623200000L;
                if(date != null)
                    time = date.getTime() + 604800000;
                testResult.setDate(new Date(time));
                testResultsRepository.save(testResult);
            }
            else {
                fighter.setQuarantineStatus("No");
                fighterRepository.save(fighter);
            }
        }

        makeMatches();

        return "redirect:/venue/";
    }

    private boolean hasThreeNegativeTests(Fighter fighter){
        List<TestResult> testResults = testResultsRepository.findByFighterId(fighter);
        if(testResults.size() < 3)
            return false;
        else{
            for(TestResult testResult : testResults.subList(testResults.size()-3, testResults.size()))
                if (testResult.getStatus().equals("Positive"))
                    return false;
            return true;
        }
    }

    private void makeMatches(){
        List<Fighter> lightWeightFighters = fighterRepository
                .findFighterByQuarantinedAndWeightClassAndMatched("No", "Lightweight", "No");
        int lightLength = (lightWeightFighters.size()%2 == 0) ? lightWeightFighters.size() : lightWeightFighters.size() -1;

        List<Fighter> middleWeightFighters = fighterRepository
                .findFighterByQuarantinedAndWeightClassAndMatched("No", "Middleweight", "No");
        int middleLength = (middleWeightFighters.size()%2 == 0) ? middleWeightFighters.size() : middleWeightFighters.size() -1;

        List<Fighter> heavyWeightFighters = fighterRepository
                .findFighterByQuarantinedAndWeightClassAndMatched("No", "Heavyweight", "No");
        int heavyLength = (heavyWeightFighters.size()%2 == 0) ? heavyWeightFighters.size() : heavyWeightFighters.size() -1;

        List<Tournament> tournaments = tournamentRepository.findTournamentByFilled("No");
        Random random = new Random();

        matchFighters(lightWeightFighters, lightLength, tournaments, random);

        matchFighters(middleWeightFighters, middleLength, tournaments, random);

        matchFighters(heavyWeightFighters, heavyLength, tournaments, random);
    }

    private void matchFighters(List<Fighter> fighters, int length, List<Tournament> tournaments, Random random) {
        for(int i = 0; i < length; i += 2){
            Fighter fighter1 = fighters.get(i);
            fighter1.setMatched("Yes");
            fighterRepository.save(fighter1);
            Fighter fighter2 = fighters.get(i+1);
            fighter2.setMatched("Yes");
            fighterRepository.save(fighter2);

            Tournament tournament = tournaments.get(random.nextInt(tournaments.size()));
            tournaments.remove(tournament);
            tournament.setFilled("Yes");
            tournamentRepository.save(tournament);

            VenueBuilder venueBuilder = new VenueBuilder();
            venueBuilder.addFighter1(fighter1.getId());
            venueBuilder.addFighter2(fighter2.getId());
            venueBuilder.addMatch(tournament.getId());
            venueRepository.save(venueBuilder.getVenue());
        }
    }

}
