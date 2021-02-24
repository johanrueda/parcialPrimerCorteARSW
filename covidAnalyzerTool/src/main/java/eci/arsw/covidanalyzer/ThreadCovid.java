package eci.arsw.covidanalyzer;

import sun.awt.Mutex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ThreadCovid extends Thread{
    TestReader testReader;
    ResultAnalyzer resultAnalyzer;
    CovidAnalyzerTool covid;
    ArrayList<File> listaCovid;
    Mutex mutex;
    boolean isPause;
    public ThreadCovid(TestReader testReader,ResultAnalyzer resultAnalyzer,CovidAnalyzerTool covid ){
        this.testReader = testReader;
        this.resultAnalyzer=resultAnalyzer;
        this.covid = covid;
        this.listaCovid = new ArrayList<File>();
        mutex = new Mutex();
        isPause=false;
    }

    @Override
    public void run(){
        for(File archivo: listaCovid){
            List<Result> transacciones = testReader.readResultsFromFile(archivo);
            for(Result transaction: transacciones) {
                while (isPause) {
                    synchronized (mutex) {
                        try {
                            mutex.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    resultAnalyzer.addResult(transaction);
                }

            }
            covid.processArchive();
        }

    }

    public void addFile(File files) {
        listaCovid.add(files);
    }

    public void pause() {
        isPause=true;
    }

    public void reanudar() {
        isPause=false;
        synchronized (mutex){
            mutex.notifyAll();
        }
    }


}
