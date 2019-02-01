package org.zouzias.tgboost.sampling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnSampler {
    private ArrayList<Integer> cols = new ArrayList<>();

    public List<Integer> col_selected;
    private int n_selected;

    /**
     *
     * @param n
     * @param sampling_rate
     */
    public ColumnSampler(int n, double sampling_rate) {
        for (int i = 0; i < n; i++) {
            cols.add(i);
        }

        n_selected = (int) (n * sampling_rate);
        col_selected = cols.subList(0, n_selected);
    }

    public void shuffle() {
        Collections.shuffle(cols);
        col_selected = cols.subList(0,n_selected);
    }
}
