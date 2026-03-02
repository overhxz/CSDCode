// CSD Mar 2013 Juansa Sendra

public class LimitedPhilo extends Philo {
    public LimitedPhilo(int id, int cycles, int delay, Table table) {
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
                table.takeL(id); delay(); table.takeR(id); //tomar forks
                table.eat(id); randomDelay();
                table.dropR(id); table.dropL(id); //dejar forks
                table.ponder(id); randomDelay();
            }
            table.end(id);
        } 
        catch (InterruptedException ex) {}
        }
}