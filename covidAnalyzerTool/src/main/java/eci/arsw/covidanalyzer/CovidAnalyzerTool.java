package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool {

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private static int amountOfFilesTotal;
    private static AtomicInteger amountOfFilesProcessed;
    private int cantHilos=25;
    private ThreadCovid[] threadCovids;
    private List<File> covidFiles;
    private static CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
    static boolean isPaused=false;




    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
        amountOfFilesProcessed.set(0);
        covidFiles = getResultFileList();
        amountOfFilesTotal = covidFiles.size();
    }

    public void processResultData() {
        threadCovids = new ThreadCovid[cantHilos];
        for(int i=0;i < cantHilos;i++){
            threadCovids[i] = new ThreadCovid(testReader,resultAnalyzer,this);
        }
        int llevo =0;
        for(File files: covidFiles){
            threadCovids[llevo].addFile(files);
            if (llevo == cantHilos - 1) {
                llevo = 0;
            } else {
                llevo++;
            }
        }
        for (ThreadCovid hilos: threadCovids){
            hilos.start();
        }
        for(ThreadCovid hilos : threadCovids){
            try {
                hilos.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Programa Finalizado");
        showReport();
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String[] args) throws Exception {
        Thread processingThread = new Thread(() -> covidAnalyzerTool.processResultData());
        processingThread.start();
        while (amountOfFilesProcessed.get() < amountOfFilesTotal) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.contains("exit")) {
                break;
            } else if (line.equals("") && !isPaused) {
                System.out.println("Programa Pausado");
                isPaused=true;
                for (ThreadCovid hilo: covidAnalyzerTool.getHilos()){
                    hilo.pause();
                }
                showReport();
            }else if(line.equals("") && isPaused){
                isPaused=false;
                System.out.println("Programa reanudado");
                for (ThreadCovid hilo : covidAnalyzerTool.getHilos()){
                    hilo.reanudar();
                }
            }
        }
        showReport();
    }

    private static void showReport(){
        String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
        Set<Result> positivePeople = covidAnalyzerTool.getPositivePeople();
        String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
        message = String.format(message, covidAnalyzerTool.amountOfFilesProcessed.get(), covidAnalyzerTool.amountOfFilesTotal, positivePeople.size(), affectedPeople);
        System.out.println(message);
    }

    private ThreadCovid[] getHilos () {
        return threadCovids;
    }


    public void processArchive () {
        amountOfFilesProcessed.incrementAndGet();
    }

}

