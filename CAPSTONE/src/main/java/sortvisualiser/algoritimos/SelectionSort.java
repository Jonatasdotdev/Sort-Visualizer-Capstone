package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;


public class SelectionSort implements UiSortAlgoritimo {

    private long stepDelay = 120;

    /**
     *  Este metodo pega o objeto SortArray e organiza seus elementos de acordo com a teoria matematica
     *  "less than"
     *  https://en.wikipedia.org/wiki/Order_theory.
     */

    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array.getValue(j) < array.getValue(minIndex)) {
                    minIndex = j;
                }
            }
            array.swap(i, minIndex, getDelay(), true);
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
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
