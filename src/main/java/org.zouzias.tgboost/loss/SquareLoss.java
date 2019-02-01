package org.zouzias.tgboost.loss;

import java.util.Arrays;

public class SquareLoss implements Loss {

    public double[] transform(double[] pred){
        return pred;
    }

    public double[] grad(double[] pred,double[] label){
        double[] ret = new double[pred.length];
        for(int i=0;i<ret.length;i++){
            ret[i] = pred[i] - label[i];
        }
        return ret;
    }

    public double[] hess(double[] pred,double[] label){
        double[] ret = new double[pred.length];
        Arrays.fill(ret,1.0);
        return ret;
    }

}
