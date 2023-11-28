package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class EvenTask extends Task<ObservableList<Integer>> {
    private final int lower;
    private final int upper;
    private final int sleepMills;

    public EvenTask(int lower,int upper,int sleep){
        this.lower = lower;
        this.upper = upper;
        this.sleepMills = sleep;
    }
    @Override
    protected ObservableList<Integer> call(){
        ObservableList<Integer> result = FXCollections.observableArrayList();
        this.updateTitle("Even Number");
        int totalWORK = this.upper - this.lower +1;
        int workDone = 0;

        for(int i = lower; i < upper;i++){
            if(this.isCancelled()){
                break;
            }
            workDone++;
            this.updateMessage("Checking if " + i + "is an even number");
            try{
                Thread.sleep(this.sleepMills);
            }
            catch (InterruptedException e){
                if(this.isCancelled()){
                    break;
                }
            }
            if(EvenNumUtil.isEven(i)){
                result.add(i);
                this.updateValue(FXCollections.observableArrayList(result));
            }
            this.updateProgress(workDone,totalWORK);
        }

        return result;
    }

    @Override
    protected void cancelled(){
        super.cancelled();
        this.updateMessage("Task was canceled");
    }
    @Override
    protected void failed(){
        super.failed();
        this.updateMessage("Task was failed");
    }

    @Override
    protected void succeeded(){
        super.succeeded();
        this.updateMessage("Task was success");
    }


















}
