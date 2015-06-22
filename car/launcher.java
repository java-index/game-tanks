package car;

public class launcher {
    public static void main(String[] args) throws MyExeption {
        Tank t34 = new T34();
        Tank tiger = new Tiger();
        BT7 bt7 = new BT7();

        Tank t = (Tank) bt7;
        BT7 tt = (BT7) t;

        System.out.println(t34);
        System.out.println(bt7);
        System.out.println(tiger);

        t34.move();
        bt7.move();
        tiger.move();
        System.out.println(Color.BLACK.ordinal());
        throw new MyExeption();
    }

}
