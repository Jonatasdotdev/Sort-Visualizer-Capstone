package src.main.java.sortvisualiser.algoritimos;
import src.main.java.sortvisualiser.SortArray;

//Base interface

public interface UiSortAlgoritimo {

    public String getName();

    public long getDelay();

    public void setDelay(long delay);

    public void runSort(SortArray array);
}


