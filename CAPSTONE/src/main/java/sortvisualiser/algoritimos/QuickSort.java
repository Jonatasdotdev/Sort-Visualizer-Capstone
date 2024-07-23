package src.main.java.sortvisualiser.algoritimos;

import src.main.java.sortvisualiser.SortArray;


public class QuickSort implements UiSortAlgoritimo {

    private long stepDelay = 30;

    /**
     * @param array Este é o array para corta e mesclar
     * @param lowIndex este é o mais a esquerda do intex da array
     * @param highIndex este e o mais a direita do index do array
     *
     */
    private int findPivotPoint(SortArray array, int lowIndex, int highIndex) {
        int pivotValue = array.getValue(highIndex);
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            if (array.getValue(j) <= pivotValue) {
                i++;
                array.swap(i, j, getDelay(), true);
            }
        }
        array.swap(i + 1, highIndex, getDelay(), true);
        return i + 1;
    }


    private void quickSort(SortArray array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotPoint = findPivotPoint(array, lowIndex, highIndex);
            quickSort(array, lowIndex, pivotPoint - 1);
            quickSort(array, pivotPoint + 1, highIndex);
        }
    }

    /**
     *  Este metodo pega o objeto SortArray e organiza seus elementos de acordo com a teoria matematica
     *  "less than"
     *  https://en.wikipedia.org/wiki/Order_theory.
     */
    @Override
    public void runSort(SortArray array) {
        quickSort(array, 0, array.arraySize() - 1);
    }

    @Override
    public String getName() {
        return "Quick Sort";
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
