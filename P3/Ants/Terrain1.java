
/**
 * Native monitor based Terrain
 * 
 * @author CSD Juansa Sendra
 * @version 2021
 */
import java.util.concurrent.* ;
import java.util.concurrent.locks.* ;

public class Terrain1 implements Terrain {
    private Viewer v;
    ReentrantLock lock;
    Condition c;
    public  Terrain1 (int t, int ants, int movs, String msg) {
        v=new Viewer(t,ants,movs,msg);
        
        lock=new ReentrantLock();
        
        c = lock.newCondition();
      
    }
    public void hi (int a) {
        lock.lock();
        try { 
            v.hi(a); 
            c.signalAll(); //a todos porque solo 1 cond
        } finally { 
            lock.unlock();
        }
    }
    
    public void bye (int a) {
        lock.lock();
        try{ 
            v.bye(a);
            c.signalAll(); //en vez de NotifyAll()
        } finally {
            lock.unlock();
        }
    
    }
    
    
    public void  move (int a) throws InterruptedException {
        lock.lock();
        try{
            v.turn(a); 
            Pos dest = v.dest(a); 
            
            while (v.occupied(dest)) {
                c.await(); 
                v.retry(a);
            }
            
            v.go(a); 
            c.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
