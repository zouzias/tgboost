package org.zouzias.tgboost.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TrainData extends Data {
    public float[][][] feature_value_index;
    public double[] label;
    public int[][] missing_index;
    public int feature_dim;
    public int dataset_size;
    private ArrayList<Integer> missing_count = new ArrayList<>();
    public float[][] origin_feature;
    private ArrayList<String> cat_features_names;
    public ArrayList<Integer> cat_features_cols = new ArrayList<>();

    public TrainData(String file,ArrayList<String> categorical_features){
        this.cat_features_names = categorical_features;
        first_scan(file);
        second_scan(file);
    }

    //to obtain: feature_dim, dataset_size,missing_count,cat_features_dim
    private void first_scan(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String header = br.readLine();
            String[] columns = header.split(",");
            feature_dim = columns.length - 1;

            for (int i=0;i<columns.length;i++) {
                if(cat_features_names.contains(columns[i])) {
                    cat_features_cols.add(i);
                }
            }

            for (int i=0;i<feature_dim;i++) {
                missing_count.add(0);
            }

            String line;
            dataset_size = 0;
            while ((line = br.readLine()) != null) {
                String[] strs = line.split(",");
                dataset_size += 1;
                for (int i=0;i<feature_dim;i++) {
                    if(strs[i].equals("")) {
                        missing_count.set(i,missing_count.get(i)+1);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //to obtain:feature_value_index,label,missing_index,origin_feature,cat_features_values
    private void second_scan(String file) {
        label = new double[dataset_size];
        missing_index = new int[feature_dim][];
        feature_value_index = new float[feature_dim][][];

        for (int i=0;i<feature_dim;i++) {
            int cnt = missing_count.get(i);
            missing_index[i] = new int[cnt];
            feature_value_index[i] = new float[dataset_size-cnt][2];
        }

        origin_feature = new float[dataset_size][feature_dim];

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();

            int[] cur_index = new int[feature_dim];
            int[] cur_missing_index = new int[feature_dim];
            Arrays.fill(cur_index,0);
            Arrays.fill(cur_missing_index,0);

            for(int row=0;row<dataset_size;row++) {
                String[] strs = br.readLine().split(",");
                label[row] = Float.parseFloat(strs[strs.length-1]);

                for(int col=0;col<feature_dim;col++) {
                    if(strs[col].equals("")) {
                        missing_index[col][cur_missing_index[col]] = row;
                        cur_missing_index[col] += 1;
                        origin_feature[row][col] = Data.NULL;
                    } else {
                        feature_value_index[col][cur_index[col]][0] = Float.parseFloat(strs[col]);
                        feature_value_index[col][cur_index[col]][1] = row;
                        cur_index[col] += 1;
                        origin_feature[row][col] = Float.parseFloat(strs[col]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
