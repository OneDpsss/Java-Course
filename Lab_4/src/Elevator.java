import java.util.HashMap;

public class Elevator {
    private Direction dir;
    private int id;
    private int curFloor,targetFloor;
    private int capacity;
    private boolean isGoing;
    private HashMap<Integer,Integer>passengers = new HashMap<>();
    public Elevator(int id,int cap){
        this.id = id;
        this.capacity = cap;
        isGoing = false;
        targetFloor = 0;
        curFloor = 1;
        dir = Direction.UP;
    }

    public Direction getDir(){
        return dir;
    }

    public void setDir(Direction dir){
        this.dir = dir;
    }

    public int getCurFloor(){
        return curFloor;
    }

    public void setCurFloor(int curFloor){
        if (curFloor < 0 || curFloor > Constants.maxFloor){
            throw new IndexOutOfBoundsException("");
        }
        this.curFloor = curFloor;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        if (capacity < 1){
            throw new IndexOutOfBoundsException("less 1");
        }
        this.capacity = capacity;
    }

    public int getTargetFloor(){
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor){
        if (targetFloor < 0 || targetFloor > Constants.maxFloor){
            throw  new IndexOutOfBoundsException("cannot be greater then 20 and lower then 0");
        }
        this.targetFloor = targetFloor;
    }

    public HashMap<Integer,Integer> getPassengers(){
        return passengers;
    }

    public boolean isGoing(){
        return isGoing;
    }

    public int getId() {
        return id;
    }

    public void makeMove(){
        this.isGoing = true;
    }
    public void makeIdle(){
        this.isGoing = false;
    }

    public void changeFloor(int delta){
        curFloor += delta;
    }
}
