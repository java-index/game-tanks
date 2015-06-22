package car;

public class Tank {

    private Color tankColor;
    private int maxSpeed;
    private int crew;

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setTankColor(Color tankColor) {
        this.tankColor = tankColor;
    }

    public int getCrew() {
        return crew;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public Color getTankColor() {
        return tankColor;
    }

    public void turn(){
        System.out.println("Tank turned");
    }

    public void move(){
        System.out.println("Tank move");
    }

    public static void main(String[] args) {

    }
}
