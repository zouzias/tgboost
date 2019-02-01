//parse the csv file, get features and label. the format: feature1,feature2,...,label
//first scan, get the feature dimension, dataset size, count of missing value for each feature
//second scan, get each feature's (value,index) and missing value indexes
//if we use ArrayList,only one scanning is needed, but it is memory consumption

package org.zouzias.tgboost;

import org.zouzias.tgboost.data.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;




