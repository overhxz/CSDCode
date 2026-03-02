// CSD Mar 2013 Juansa Sendra

public class BothOrNoneTable extends RegularTable { //both or none
    public BothOrNoneTable(StateManager state) {super(state);}
    
    @Override
    public synchronized void takeLR(int id) throws InterruptedException{
        while (!state.rightFree(id) || !state.leftFree(id)) { wait();}
        state.takeR(id);
        state.takeL(id);
    }
    
    public synchronized void dropLR(int id){
        state.dropR(id);
        state.dropL(id);
    }
}
