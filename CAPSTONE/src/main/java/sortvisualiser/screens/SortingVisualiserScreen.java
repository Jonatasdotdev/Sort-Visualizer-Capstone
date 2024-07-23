package src.main.java.sortvisualiser.screens;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import src.main.java.sortvisualiser.MainApp;
import src.main.java.sortvisualiser.SortArray;
import src.main.java.sortvisualiser.algoritimos.UiSortAlgoritimo;


public final class SortingVisualiserScreen extends Screen {
    private final SortArray sortArray;
    private final ArrayList<UiSortAlgoritimo> sortQueue;

    /**
     *Cria o GUI
     */
    public SortingVisualiserScreen(ArrayList<UiSortAlgoritimo> algorithms, MainApp app) {
        super(app);
        setLayout(new BorderLayout());
        sortArray = new SortArray(false); // Removed the playSounds parameter
        add(sortArray, BorderLayout.CENTER);
        sortQueue = algorithms;
    }

    private void longSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        longSleep();
    }

    @Override
    public void onOpen() {

        //Bloqueia a EventDispatchThread e precisa rodar em uma worker thread.

        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (UiSortAlgoritimo algorithm : sortQueue) {
                    shuffleAndWait();

                    sortArray.setName(algorithm.getName());
                    sortArray.setAlgorithm(algorithm);

                    algorithm.runSort(sortArray);
                    sortArray.resetColours();
                    sortArray.highlightArray();
                    sortArray.resetColours();
                    longSleep();
                }
                return null;
            }

            @Override
            public void done() {
                app.popScreen();
            }
        };

        swingWorker.execute();
    }
}
