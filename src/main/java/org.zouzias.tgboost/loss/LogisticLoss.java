package org.zouzias.tgboost.loss;

/**
 * Logistic loss function
 */
public class LogisticLoss implements Loss{

    @Override
    public double[] transform(double[] pred){
        double[] ret = new double[pred.length];
        for(int i=0;i<ret.length;i++){
            ret[i] = clip(1.0 / (1.0 + Math.exp(-pred[i])));
        }
        return ret;
    }

    @Override
    public double[] grad(double[] pred,double[] label){
        double[] pred1 = transform(pred);
        double[] ret = new double[pred1.length];
        for(int i=0;i<ret.length;i++){
            ret[i] = pred1[i] - label[i];
        }
        return ret;
    }

    @Override
    public double[] hess(double[] pred,double[] label){
        double[] pred1 = transform(pred);
        double[] ret = new double[pred.length];
        for(int i=0;i<ret.length;i++){
            ret[i] = pred1[i]*(1.0 - pred1[i]);
        }
        return ret;
    }

    private double clip(double val){
        if(val<0.00001){
            return 0.00001;
        }else if(val>0.99999){
            return 0.99999;
        }else {
            return val;
        }
    }
}
