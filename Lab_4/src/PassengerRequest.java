public class PassengerRequest {
    private int targetFloor,curFloor,id;
    private Direction dir;
    private boolean isGoing = false;

    public PassengerRequest(int id, int current,int target){
        this.id = id;
        targetFloor = target;
        curFloor = current;
        dir = target < current ? Direction.DOWN : Direction.UP;
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


    public int getCurFloor(){
        return curFloor;
    }
    public void setCurFloor(int curFloor){
        if (curFloor < 0 || curFloor > Constants.maxFloor){
            throw  new IndexOutOfBoundsException("cannot be greater then 20 and lower then 0");
        }
        this.curFloor = curFloor;
    }

    public boolean isGoing(){
        return isGoing;
    }
    public void setGoing(boolean status){
        isGoing = status;
    }

    public Direction getDir() {
        return dir;
    }

    public int getId() {
        return id;
    }
}
