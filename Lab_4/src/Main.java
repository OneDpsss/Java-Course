import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        Building b = new Building();
        ScheduledExecutorService s = Executors.newScheduledThreadPool(2);
        s.scheduleAtFixedRate(new Runnable() {
            int id = 0;
            @Override
            public void run() {
                int targetFloor;
                int curFloor = ThreadLocalRandom.current().nextInt(0,Constants.maxFloor + 1);
                do{
                    targetFloor = ThreadLocalRandom.current().nextInt(0,Constants.maxFloor + 1);
                }while (curFloor == targetFloor);
                id++;
              //  System.out.println("id " + id + " curFloor " + curFloor +  " targetFloor " + targetFloor);
                b.addInQueue(new PassengerRequest(id,curFloor,targetFloor));
            }
        },0,3, TimeUnit.SECONDS);
        s.scheduleAtFixedRate(new Runnable() {
            Elevator elevator1 = new Elevator(1,Constants.maxCapacity);
            Elevator elevator2 = new Elevator(2,Constants.maxCapacity);
            @Override
            public void run() {
                b.visitFloor(elevator1);
                b.visitFloor(elevator2);
            }
        },0,1, TimeUnit.SECONDS);
    }
}
