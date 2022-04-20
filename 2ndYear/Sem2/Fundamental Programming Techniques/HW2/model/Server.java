package model;

import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Server extends TimerTask implements Runnable
{

    private final int id;
    private int waitingTime;
    private int totalWaitingTime;
    private BlockingQueue<Task> tasks;
    public Server(int id, int maxqueue){
        this.id=id;
        this.waitingTime=0;
        this.totalWaitingTime=0;
        this.tasks = new ArrayBlockingQueue<>(maxqueue);
    }

    @Override
    public void run()
    {
        while (true)
        {
            while (tasks.peek() != null)
            {
                Task t = tasks.peek();
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                this.waitingTime--;
                t.setProcessingPeriod(t.getProcessingPeriod()-1);
                if (t.getProcessingPeriod() == 0)
                {
                    try {
                        tasks.take();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public void add_Task(Task newTask) {
        tasks.add(newTask);
        waitingTime+=newTask.getProcessingPeriod();
    }
    public int getId() {
        return id;
    }
    public int getWaitingTime(){
        return this.waitingTime;
    }
    public int getTotalWaitingTime() {
        return totalWaitingTime;
    }
    public int getQueue_size(){
        return this.tasks.size();
    }
    public BlockingQueue<Task> getTasks() {
        return tasks;
    }





    @Override
    public String toString()
    {
        String result="";
        if( getWaitingTime() <= 0 || tasks.peek() == null || tasks.peek().getProcessingPeriod() <= 0)
        {
            result = "closed";
        }
        else
        {
            result = tasks.toString();
        }

        return result;
    }

}
