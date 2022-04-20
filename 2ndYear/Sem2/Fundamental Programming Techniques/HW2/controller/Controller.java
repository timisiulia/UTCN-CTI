package controller;

import view.View;
import model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Thread t;
    private View view;
    private SimulationManager simulator;

    public Controller(SimulationManager s, View v){

        this.view=v;
        this.simulator=s;
        t = new Thread(s);
        v.summitListener(new summitActionListener());
        v.startListener(new startActionListener());
    }

    public class startActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            view.reset();
            Thread simThread = new Thread(simulator);
            simThread.start();
            try {
                simThread.join();
            } catch (Exception ex) {
            }
        }
    }

    public class summitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int N,Q,timeLim,tMaxA,tMinA,tMaxS,tMinS;
            N = Integer.parseInt(view.getN().getText());
            Q = Integer.parseInt(view.getQ().getText());
            timeLim = Integer.parseInt(view.getTimeLim().getText());
            tMaxA = Integer.parseInt(view.gettMaxA().getText());
            tMinA = Integer.parseInt(view.gettMinA().getText());
            tMaxS = Integer.parseInt(view.gettMaxS().getText());
            tMinS = Integer.parseInt(view.gettMinS().getText());
            simulator.setNrServers(Q);
            simulator.setNrClients(N);
            simulator.settMaxsimulation(timeLim);
            simulator.settMaxArrival(tMaxA);
            simulator.settMinArrival(tMinA);
            simulator.settMaxService(tMaxS);
            simulator.settMinService(tMinS);
            simulator.generateNRandomTasks();

        }
    }

}






