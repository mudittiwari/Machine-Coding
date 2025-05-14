package Player;

public class Player {
    private String playerName;
    private int currentLocation;

    public Player(String playerName, int currentLocation){
        this.playerName = playerName;
        this.currentLocation =  currentLocation;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(int currentLocation) {
        this.currentLocation = currentLocation;
    }
}
