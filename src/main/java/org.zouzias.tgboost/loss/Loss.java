package org.zouzias.tgboost.loss;

public interface Loss {

    double[] grad(double[] pred,double[] label);

    double[] hess(double[] pred,double[] label);

    double[] transform(double[] pred);
}


