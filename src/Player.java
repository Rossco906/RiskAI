import java.util.ArrayList;
import java.util.HashMap;

public abstract class Player implements Observer {

    protected HashMap<String, Region> ownedRegions;
    protected PlayerEnum whoAmI;
    private static int observerIDTracker = 0;
    private int observerID = 0;

    /*public Player(ArrayList<Region> ownedRegions, PlayerEnum player){
        this.ownedRegions = ownedRegions;
        this.whoAmI = player;
        observerID = ++observerIDTracker;
        System.out.println("New Player created called " + player + " id No. "+ observerIDTracker);
        for(Subject s : ownedRegions){
            s.register(this);
        }
    }*/
    public Player(PlayerEnum player){
        ownedRegions = new HashMap<>();
        this.whoAmI = player;
        observerID = ++observerIDTracker;
        System.out.println("New Player created called " + player + " id No. "+ observerIDTracker);
    }
    public void addOwnedRegion(Region r){
        ownedRegions.put(r.toString(), r);
        r.register(this);
    }
    @Override
    public void update(Region region) {
        if(!(region.getOwner()==whoAmI )){
            region.unregister(this);
            ownedRegions.remove(region);

        }
    }

    @Override
    public int getObserverID() {
        return observerID;
    }
    public abstract void startTurn(GameState state, GameStateMediator mediator);
}
