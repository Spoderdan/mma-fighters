package sd.a1.models;

public class VenueBuilder {

    private Integer fighter1;
    private Integer fighter2;
    private Integer match;

    public void addFighter1(Integer fighter1){
        this.fighter1 = fighter1;
    }

    public void addFighter2(Integer fighter2){
        this.fighter2 = fighter2;
    }

    public void addMatch(Integer match){
        this.match = match;
    }

    public Venue getVenue(){
        Venue venue = new Venue();

        venue.setFighter1(fighter1);
        venue.setFighter2(fighter2);
        venue.setMatch(match);

        return venue;
    }

}
