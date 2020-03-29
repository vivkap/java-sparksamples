package com.vivek.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class ReadCsvApp {



    public static void main(String[] args) throws Exception {
       /* if (args.length < 1) {
            System.err.println("Usage: JavaWordCount <file>");
            System.exit(1);
        }*/

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("ReadCsv");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        SQLContext   sq = new SQLContext(ctx);


     //  DataFrame df = sq.read().format("com.databricks.spark.csv").option("header","true").load("C:\\HR_Data.csv");

        DataFrame df = sq.read().format("com.databricks.spark.csv").option("header","true").load("file:///root/HR_Data.csv");
        // df.printSchema();
        // df.show();

        df.registerTempTable("hrdata");

        DataFrame df1 = sq.sql("select satisfaction_level + 5,upper(sales) sales, salary from hrdata");

        df1.printSchema();
        df1.show();

        ctx.stop();
    }
}
