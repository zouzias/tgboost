package org.zouzias.tgboost.examples;


import org.zouzias.tgboost.sampling.ColumnSampler;
import org.zouzias.tgboost.sampling.RowSampler;

public class Sampling {
    public static void main(String[] args) {
        //test case
        RowSampler rs = new RowSampler(1000000, 0.8);
        System.out.println(rs.row_mask.subList(0,20));
        rs.shuffle();
        System.out.println(rs.row_mask.subList(0,20));
        int sum = 0;
        for(double v:rs.row_mask){
            sum += v;
        }
        System.out.println(sum);

        ColumnSampler cs = new ColumnSampler(1000, 0.6);
        System.out.println(cs.col_selected.subList(0,20));
        cs.shuffle();
        System.out.println(cs.col_selected.subList(0,20));
        System.out.println(cs.col_selected.size());
    }
}