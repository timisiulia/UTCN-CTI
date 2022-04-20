package model;

import model.Files;
import model.Scheduler;
import model.SelectionPolicy;
import model.Task;
import view.View;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager extends Files implements Runnable  {
    public int nrClients = 100;
    public int nrServers = 2;
    public int tMaxsimulation ;
    public int tMinArrival  = 2;
    public int tMaxArrival= 30;
    public int tMinService = 2;
    public int tMaxService= 10;
    public double avgerageTime=0;
    public double avgerageProcesingTime=0;
    public int pick=0;
    int nrPers=0;
    String result;
    private Scheduler scheduler;
    private File outFile;
    public List<Task> generatedTasks;
    public SelectionPolicy selectionPolicy = SelectionPolicy.shortest_time;
    public View v;
    public SimulationManager(File file, File outFile,View v) {
        ArrayList<String> fileResult;
        try {
            fileResult = readFromFile(file);
            this.v=v;

            this.outFile = outFile;

            try {
                this.outFile.createNewFile();
            } catch (Exception exceptie) {
                System.out.println("Fisierul nu exista sau nu s-a putut crea ");
                return;
            }
        } catch (Exception exceptie) {
            System.out.println(exceptie.getMessage());
            return;
        }



    }
    public void generateNRandomTasks() {
        scheduler = new Scheduler(nrServers, nrClients,selectionPolicy);
        generatedTasks = new ArrayList<Task>(nrClients);
        for (int i = 0; i < nrClients; i++) {
            Task t = new Task(randomArrivingTime(), randomProcessingTime(), i);
            generatedTasks.add(t);
        }
        Collections.sort(generatedTasks);
    }

    private int getMaxWait(int maxWait) {
        if (generatedTasks.isEmpty())
        {
            maxWait = scheduler.getMaxWaitQueue();
        } else {
            maxWait--;
        }

        return maxWait;
    }

    public int randomProcessingTime() {
        return (tMinService  + (int) (Math.random() * (tMaxService -tMinService)));
    }

    public int randomArrivingTime() {
        return (tMinArrival + (int) (Math.random() * (tMaxArrival - tMinArrival)));
    }

    public void run() {
        FileWriter fWriter;
        String tot=new String();

        try {
            fWriter = new FileWriter(this.outFile.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        int currentTime = 0;
        int maxWait = 0;
        int nrProcessedClients = 0;
        while (currentTime < tMaxsimulation  && (!generatedTasks.isEmpty() || maxWait > 0)) {
            while (!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                if (currentTime + generatedTasks.get(0).getProcessingPeriod() < tMaxsimulation ) {
                    nrProcessedClients++;
                    int aaa=generatedTasks.get(0).getProcessingPeriod();
                    avgerageTime += aaa+generatedTasks.get(0).getArrivalTime()-currentTime;
                    avgerageProcesingTime+=aaa;

                }
                generatedTasks.remove(0);
            }
            //result=new String();
            result = getResult(currentTime);

            System.out.println(result);
            tot+=result;




            try {
                fWriter.write(result);
            } catch (Exception ex) {
            }
            maxWait = getMaxWait(maxWait);
            currentTime++;
            try {
                Thread.sleep(1000);

            } catch (Exception ex) {
            }

        }
        try {

            String aaa=new String();
            aaa+="\n";
            fWriter.write("Average waiting time: " +(avgerageTime-1) / nrProcessedClients+"\n");
            aaa+="Average waiting time: " +(avgerageTime-1) / nrProcessedClients+"\n";
            fWriter.write("Average service time: " +avgerageProcesingTime / nrProcessedClients+"\n");
            aaa+="Average service time: " +avgerageProcesingTime / nrProcessedClients+"\n";
            fWriter.write("Pick Hour: " +pick+"\n");
            aaa+="Pick Hour: " +pick+"\n";
            v.setTotal(tot+aaa);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try {
            fWriter.close();
        } catch (Exception ex) {
        }
    }


    private String getResult(int currentTime) {

        String result = "\nTime: " + currentTime + "\n";
        result = result + "Waiting clients: ";
        for (Task i : generatedTasks) {
            result = result + i.toString();
        }
        result = result + "\n" + scheduler.toString();
        if(scheduler.getNn()>this.nrPers){
            nrPers=scheduler.getNn();
            this.pick=currentTime;
        }


        return result;
    }

    public void setNrClients(int nrClients) {
        this.nrClients = nrClients;
    }

    public void setNrServers(int nrServers) {
        this.nrServers = nrServers;
    }

    public void settMaxsimulation(int tMaxsimulation) {
        this.tMaxsimulation = tMaxsimulation;
    }

    public void settMinArrival(int tMinArrival) {
        this.tMinArrival = tMinArrival;
    }

    public void settMaxArrival(int tMaxArrival) {
        this.tMaxArrival = tMaxArrival;
    }

    public void settMinService(int tMinService) {
        this.tMinService = tMinService;
    }

    public void settMaxService(int tMaxService) {
        this.tMaxService = tMaxService;
    }
}
