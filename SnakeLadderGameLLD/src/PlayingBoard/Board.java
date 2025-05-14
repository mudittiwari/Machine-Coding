package PlayingBoard;

import Ladder.Ladder;
import Player.Player;
import com.sun.org.apache.xpath.internal.operations.Bool;
import snake.Snake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements BoardInterface{
    private int totalLadders;
    private int totalPlayers;
    private int totalSnakes;

    private List<Ladder> ladders;
    private List<Player> players;
    private List<Snake> snakes;
    private List<String> playersWon;

    public Board(int ladders, int players, int snakes){
        this.totalLadders = ladders;
        this.totalPlayers = players;
        this.totalSnakes = snakes;
        this.players = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.playersWon = new ArrayList<>();
    }

    public int getTotalLadders() {
        return this.totalLadders;
    }

    public void setTotalLadders(int totalLadders) {
        this.totalLadders = totalLadders;
    }

    public int getTotalPlayers() {
        return this.totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public int getTotalSnakes() {
        return this.totalSnakes;
    }

    public void setTotalSnakes(int totalSnakes) {
        this.totalSnakes = totalSnakes;
    }

    public List<String> getPlayersWon() {
        return this.playersWon;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addSnake(int start, int end){
        Snake snake = new Snake(start, end);
        this.snakes.add(snake);
    }

    public void addPlayer(String name, int location){
        Player player = new Player(name, location);
        this.players.add(player);
    }

    public void addLadder(int start, int end){
        Ladder ladder = new Ladder(start, end);
        this.ladders.add(ladder);
    }

    public Boolean updatePlayerLocation(String playerName, int diceValue){
        for (Player player : players) {
            if(player.getPlayerName().equals(playerName)){
                if(player.getCurrentLocation() == 0 && diceValue != 6)
                    return false;
                if(player.getCurrentLocation() + diceValue > 100)
                    return false;
                if(player.getCurrentLocation() == 0 && diceValue == 6){
                    player.setCurrentLocation(1);
                    return true;
                }
                player.setCurrentLocation(player.getCurrentLocation()+diceValue);

                //check snake hit
                int snakeHit = this.checkSnakeHit(playerName);
                if(snakeHit != -1)
                    player.setCurrentLocation(snakeHit);

                //check ladder hit
                int ladderHit = this.checkLadderHit(playerName);
                if(ladderHit != -1)
                    player.setCurrentLocation(ladderHit);

                //check if player has won
                if(this.checkPlayerWin(playerName))
                    this.playersWon.add(playerName);
                return true;
            }
        };
        return false;
    }

    private int checkLadderHit(String playerName){
        for(Player player : players){
            if(player.getPlayerName().equals(playerName)){
                int location = player.getCurrentLocation();
                for (Ladder ladder : ladders){
                    if(ladder.getPositionX() == location)
                        return ladder.getPositionY();
                }
            }
        }
        return -1;
    }

    private int checkSnakeHit(String playerName){
        for(Player player : players){
            if(player.getPlayerName().equals(playerName)){
                int location = player.getCurrentLocation();
                for (Snake snake : snakes){
                    if(snake.getStart() == location)
                        return snake.getEnd();
                }
            }
        }
        return -1;
    }

    private Boolean checkPlayerWin(String playerName){
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName) && player.getCurrentLocation() == 100) {
                return true;
            }
        }
        return false;
    }

}
