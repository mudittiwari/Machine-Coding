package Ladder;

public class Ladder {
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Ladder(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

}
