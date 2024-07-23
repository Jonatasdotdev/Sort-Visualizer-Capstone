package src.main.java.sortvisualiser.screens;

import src.main.java.sortvisualiser.MainApp;
import src.main.java.sortvisualiser.algoritimos.BubbleSort;
import src.main.java.sortvisualiser.algoritimos.QuickSort;
import src.main.java.sortvisualiser.algoritimos.SelectionSort;
import src.main.java.sortvisualiser.algoritimos.UiSortAlgoritimo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public final class MainMenuScreen extends Screen {
    private static final Color BACKGROUND_COLOUR = Color.DARK_GRAY;
    private final ArrayList<AlgorithmCheckBox> checkBoxes;
    private final MainApp app;

    public MainMenuScreen(MainApp app) {
        super(app); // Certifique-se de chamar o construtor da classe base
        this.app = app;
        checkBoxes = new ArrayList<>();
        setUpGUI();
    }

    private void addCheckBox(UiSortAlgoritimo algorithm, JPanel panel) {
        JCheckBox box = new JCheckBox(algorithm.getName(), true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        checkBoxes.add(new AlgorithmCheckBox(algorithm, box));
        panel.add(box);
    }

    private void initContainer(JPanel p) {
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBackground(BACKGROUND_COLOUR);
    }

    public void setUpGUI() {
        JPanel sortAlgorithmContainer = new JPanel();
        JPanel optionsContainer = new JPanel();
        JPanel outerContainer = new JPanel();
        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgorithmContainer);

        outerContainer.setBackground(BACKGROUND_COLOUR);
        outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS));

        sortAlgorithmContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCheckBox(new BubbleSort(), sortAlgorithmContainer);
        addCheckBox(new SelectionSort(), sortAlgorithmContainer);
        addCheckBox(new QuickSort(), sortAlgorithmContainer);


        JButton startButton = new JButton("Begin Visual Sorter");
        startButton.addActionListener((ActionEvent e) -> {
            ArrayList<UiSortAlgoritimo> algorithms = new ArrayList<>();
            for (AlgorithmCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    algorithms.add(cb.getAlgorithm());
                }
            }
            app.pushScreen(
                    new SortingVisualiserScreen(
                            algorithms,
                            app
                    ));
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        outerContainer.add(optionsContainer);
        outerContainer.add(Box.createRigidArea(new Dimension(5, 0)));
        outerContainer.add(sortAlgorithmContainer);

        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(outerContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton);
    }

    @Override
    public void onOpen() {
        checkBoxes.forEach((box) -> {
            box.unselect();
        });
    }

    private class AlgorithmCheckBox {
        private final UiSortAlgoritimo algorithm;
        private final JCheckBox box;

        public AlgorithmCheckBox(UiSortAlgoritimo algorithm, JCheckBox box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }

        public void unselect() {
            box.setSelected(false);
        }

        public boolean isSelected() {
            return box.isSelected();
        }

        public UiSortAlgoritimo getAlgorithm() {
            return algorithm;
        }
    }
}
