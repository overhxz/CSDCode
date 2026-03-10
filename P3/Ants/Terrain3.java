
import java.util.concurrent.* ;
import java.util.concurrent.locks.* ;
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */


public class Terrain3 implements Terrain {
    private Viewer v;
    ReentrantLock lock;
    Condition[][] c;
    public  Terrain3 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        
        lock=new ReentrantLock();
        c = new Condition[t][t];
         for(int i = 0; i< t ; i++){
             for(int j = 0; j< t ; j++){
                c[i][j] = lock.newCondition();
                
            }
        }
    }
    
    /** en hi y bye hace falta avisar a todos */
    public void hi (int a) {
        lock.lock();
        try { 
            v.hi(a); 
            //c.signalAll(); //a todos porque solo 1 cond
        } finally { 
            lock.unlock();
        }
    }
    
    public void bye (int a) {
        lock.lock();
        try{ 
            v.bye(a);
            Pos pos = v.getPos(a);
            c[pos.x][pos.y].signalAll(); //en vez de NotifyAll()
        } finally {
            lock.unlock();
        }
    
    }
    
    
    public void  move (int a) throws InterruptedException {
        lock.lock();
        try{
            v.turn(a); 
            Pos dest = v.dest(a); 
            Pos pos = v.getPos(a);
            
            while (v.occupied(dest)) {
                c[dest.x][dest.y].await(); //sustituido por wait
                v.retry(a);
            }
            
            v.go(a); 
            c[dest.x][dest.y].signalAll();
        } finally {
            lock.unlock();
        }
    }
}
