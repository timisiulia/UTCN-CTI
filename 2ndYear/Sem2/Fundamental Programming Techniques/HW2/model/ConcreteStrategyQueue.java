package model;

import model.Server;
import model.Strategy;

import java.util.List;
public class ConcreteStrategyQueue implements Strategy {
    public void add_Task (List<Server> servers, Task t)
    {
        int qmin = 100000;
        int id = 0;
        for(Server i : servers)
            if(i.getTasks().size()< qmin) {
                qmin = i.getTasks().size();
                id = i.getId();
            }
        for(Server i : servers) {
            if(i.getId() == id) {
                i.add_Task(t);

            }
        }
    }

}
