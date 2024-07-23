package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;


public class BubbleSort implements UiSortAlgoritimo {

    private long stepDelay = 2;

    /**
     * Este metodo implementa o algoritimo bubblesort pega um objeto
     * sortarray e organiza seus elementos seguindo a teoria "menos que",
     * https://en.wikipedia.org/wiki/Order_theory.
     */

    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.swap(j, j + 1, getDelay(), true);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public long getDelay() {
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }
}
