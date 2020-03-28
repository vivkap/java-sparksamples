package com.vivek.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class Covid19Analysis {

    public static void main(String[] args) throws Exception {

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("Covid19Analysis");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        SQLContext   sq = new SQLContext(ctx);

        DataFrame df = sq.read().format("com.databricks.spark.csv").option("header","true").load("file:///root/covid19_confirmed_global.csv");
        // df.printSchema();
        // df.show();

/*
        df.registerTempTable("m3countyfacts");

        DataFrame m3county = sq.sql("select fips, lower(area_name) area_name, POP060210 from m3countyfacts");

        m3county.printSchema();
*/

        Row[] listOfRows = df.collect();

        for (Row  row:  listOfRows )
        {
            System.out.println(row);
        }

        ctx.stop();
    }
}
