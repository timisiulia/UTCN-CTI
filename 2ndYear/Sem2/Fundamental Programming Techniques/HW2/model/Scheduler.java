package model;

import java.util.ArrayList;
import java.util.List;
public class Scheduler {
    private List<Server> servers;
    private List<Thread> threads;
    private int nrServers;
    private int nrTasksPerServer;
    private Strategy strategy;
    SelectionPolicy policy;
    private int nn=0;
    public Scheduler(int numbServers, int tasksforServer, SelectionPolicy p)
    {
        this.nrServers = numbServers;
        this.newStrategy(p);
        this.servers = new ArrayList<>(numbServers);
        this.nrTasksPerServer = tasksforServer;
        this.threads = new ArrayList<>(numbServers);

        for(int i = 0; i < numbServers; i++){
            Server s = new Server(i, tasksforServer);
            servers.add(s);
            Thread t = new Thread(s,"Q"+(i+1));
            threads.add(t);
            t.start();

        }

    }
    public int getNn(){
        return this.nn;
    }
    public void newStrategy(SelectionPolicy policy) {
        if(policy == SelectionPolicy.shortest_time)
            strategy = new ConcreteStrategyTime();
        if(policy == SelectionPolicy.shortest_queue)
            strategy = new ConcreteStrategyQueue();

    }
    public int getMaxWaitQueue()
    {
        int maxwait = 0;
        for(Server q : servers)
        {
            if (q.getWaitingTime()>maxwait) {
                maxwait=q.getWaitingTime();
            }
        }
        return maxwait;
    }
    public List<Thread> getThreads(){
        return this.threads;
    }
    public void dispatchTask(Task t){
        strategy.add_Task(servers,t);
    }
    public Server getServer(int i){
        return this.servers.get(i);
    }
    public int getServersSize(){
        return this.servers.size();
    }
    public List<Server> getServers(){
        return this.servers;
    }
    public void stopThreads(){
        for (Thread t:threads) {
            t.stop();
        }
    }
    public String toString() {
        String rezultat="";
        for (Server i: servers) {
            rezultat+= "Queue " + i.getId() + ": " + i.toString() + "\n";
            int nnn=i.getQueue_size();
            if (nnn>nn){
                nn=nnn;
            }

        }


        return rezultat;
    }





}