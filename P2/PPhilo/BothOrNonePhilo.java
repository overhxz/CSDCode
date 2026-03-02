// CSD Mar 2013 Juansa Sendra


public class BothOrNonePhilo extends Philo { //Both or None. Table3
    public BothOrNonePhilo(int id, int cycles, int delay, Table table) {
        super(id,cycles,delay,table);
    }
    
    @Override
    protected void delay() throws InterruptedException {super.delay();}

    @Override
    protected void randomDelay() throws InterruptedException {
        super.randomDelay();
    }
    
    @Override
    public void run(){
        try {
            table.begin(id);
            for (int i=0; i<cycles; i++) {
                table.takeLR(id); delay(); //tomar forks
                table.eat(id); randomDelay();
                table.dropLR(id); //dejar forks
                table.ponder(id); randomDelay();
            }
            table.end(id);
        } 
        catch (InterruptedException ex) {}
    }
}