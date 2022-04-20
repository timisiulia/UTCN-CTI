package model;

import java.util.List;
public interface Strategy {
    void add_Task(List<Server> servers, Task t);

    // void add_Task(Task t);
}

