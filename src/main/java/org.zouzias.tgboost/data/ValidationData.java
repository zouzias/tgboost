package org.zouzias.tgboost.data;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidationData extends Data {
  public int feature_dim;
  public int dataset_size;
  public float[][] origin_feature;
  public double[] label;

  public ValidationData(String file) {
    first_scan(file);
    second_scan(file);
  }

  private void first_scan(String file) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String header = br.readLine();
      feature_dim = header.split(",").length - 1;

      String line;
      dataset_size = 0;
      while((line = br.readLine()) != null) {
        dataset_size += 1;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void second_scan(String file) {
    label = new double[dataset_size];
    origin_feature = new float[dataset_size][feature_dim];

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      br.readLine();
      for (int row=0;row<dataset_size;row++) {
        String[] strs = br.readLine().split(",");
        label[row] = Float.parseFloat(strs[strs.length-1]);
        for (int col=0;col<feature_dim;col++) {
          if (strs[col].equals("")) {
            origin_feature[row][col] = Data.NULL;
          } else {
            origin_feature[row][col] = Float.parseFloat(strs[col]);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
