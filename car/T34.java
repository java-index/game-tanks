package car;

public class T34 extends Tank {
    @Override
    public String toString() {
        return "This is T34 class";
    }

    @Override
    public void move() {
        System.out.println("T34 move");
    }
}
