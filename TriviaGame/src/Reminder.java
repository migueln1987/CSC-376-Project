import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    Timer timer;
    boolean isTaskCompleted = false;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
	}

    class RemindTask extends TimerTask {
        public void run() {
        	try {
            System.out.println("Time's up!");
       
            
            timer.cancel(); //Terminate the timer thread
            
            IOException e = new IOException();
            throw e;
        	}
        	catch (Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }

}