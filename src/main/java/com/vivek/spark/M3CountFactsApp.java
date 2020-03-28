package com.vivek.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class M3CountFactsApp {



    public static void main(String[] args) throws Exception {
       /* if (args.length < 1) {
            System.err.println("Usage: JavaWordCount <file>");
            System.exit(1);
        }*/

        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("M3CountyFacts");
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        SQLContext   sq = new SQLContext(ctx);

        DataFrame df = sq.read().format("com.databricks.spark.csv").option("header","true").load("file:///root/m3_county_facts.csv");
        // df.printSchema();
        // df.show();

        df.registerTempTable("m3countyfacts");

        DataFrame m3county = sq.sql("select fips, lower(area_name) area_name, POP060210 from m3countyfacts");

        m3county.printSchema();

        Row[] listOfRows = m3county.collect();

        for (Row  row:  listOfRows )
        {
            System.out.println(row);
        }

        ctx.stop();
    }
}
