package org.zouzias.tgboost.sampling;

import java.util.ArrayList;
import java.util.Collections;

public class RowSampler {
    public ArrayList<Double> row_mask = new ArrayList<>();

    public RowSampler(int n, double sampling_rate) {
        for (int i=0;i<n;i++) {
            this.row_mask.add(Math.random()<=sampling_rate? 1.0 : 0.0);
        }
    }

    public void shuffle() {
        Collections.shuffle(this.row_mask);
    }
}
