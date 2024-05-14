import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Building {
    private ArrayDeque<PassengerRequest> queue = new ArrayDeque<>();
    private Map<Integer,ArrayDeque<PassengerRequest>> action = new HashMap<>();

    private void EnterPassenger(Elevator elevator){
        int curFloor = elevator.getCurFloor();
        if (!action.containsKey(curFloor) || action.get(curFloor).isEmpty()){
            return;
        }
        Direction elevatorDir = elevator.getDir();
        int elevatorTargetFloor = elevator.getTargetFloor();
        Deque<PassengerRequest> unprocess = new ArrayDeque<>();
        for (PassengerRequest passenger : action.get(curFloor)){
            int passTargetFloor = passenger.getTargetFloor();
            Direction passDir = passenger.getDir();

            if (curFloor == elevatorTargetFloor || passDir == elevatorDir){
                elevator.makeMove();
                elevator.setDir(passDir);
                elevator.getPassengers().put(passenger.getId(), passTargetFloor);

                if (passTargetFloor > elevatorTargetFloor || passTargetFloor < elevatorTargetFloor){
                    elevator.setTargetFloor(passTargetFloor);
                }
                System.out.printf("Passenger %d comes in %d and goes to %d\n",passenger.getId(),passenger.getCurFloor(),passTargetFloor);
                unprocess.add(passenger);
            }
        }
        queue.removeAll(unprocess);
        action.get(curFloor).removeAll(unprocess);
    }

    private void LeavePassenger(Elevator elevator) {
        Deque<Integer> removeID = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : elevator.getPassengers().entrySet()) {
            if (entry.getValue() == elevator.getCurFloor()) {
                System.out.printf("Passenger %d leave on the floor %d\n", entry.getKey(), entry.getValue());
                removeID.add(entry.getKey());
            }
        }
        for (int id : removeID){
            elevator.getPassengers().remove(id);
        }
    }

    public synchronized void addInQueue(PassengerRequest request){
        if (!action.containsKey(request.getCurFloor())){
            action.put(request.getCurFloor(),new ArrayDeque<>());
        }
        action.get(request.getCurFloor()).add(request);
        queue.add(request);
        System.out.printf("Passenger %d wants to travel from %d to %d floor\n",request.getId(),request.getCurFloor(),request.getTargetFloor());
    }

    public synchronized void visitFloor(Elevator elevator){
        System.out.printf("Elevator %d is on %d floor\n",elevator.getId(),elevator.getCurFloor());
        LeavePassenger(elevator);
        EnterPassenger(elevator);
        if (queue.isEmpty() && elevator.getPassengers().isEmpty()){
            System.out.println("No passengers now");
            elevator.makeMove();
            return;
        }
        if (queue.isEmpty()){
            elevator.makeIdle();
        }
        if (!elevator.isGoing()){
            for (PassengerRequest passenger : queue){
                if (!passenger.isGoing()){
                    passenger.setGoing(true);
                    elevator.setTargetFloor(passenger.getTargetFloor());
                    if (passenger.getCurFloor() > elevator.getCurFloor()) elevator.setDir(Direction.UP);
                    else elevator.setDir(Direction.DOWN);
                    elevator.makeMove();
                    break;
                }
            }
        }


        if (!elevator.isGoing()) return;

        if (elevator.getDir() == Direction.UP && (elevator.getCurFloor() == elevator.getTargetFloor() || elevator.getCurFloor() == Constants.maxFloor)){
            elevator.setDir(Direction.DOWN);
            elevator.makeIdle();
        }else if (elevator.getDir() == Direction.DOWN && (elevator.getCurFloor() == elevator.getTargetFloor() || elevator.getCurFloor() == 0)){
            elevator.setDir(Direction.UP);
            elevator.makeIdle();
        }
        if (elevator.getDir() == Direction.UP){
            elevator.changeFloor(1);
        }
        else{
            elevator.changeFloor(-1);
        }
    }
}
