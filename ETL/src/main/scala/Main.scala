package etl

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._


object Main {
  val spark = SparkSession.builder()
    .appName("Analise-Produtos")
    .master("local[*]")
    .getOrCreate()
  spark.conf.set("spark.hadoop.fs.s3a.endpoint", "s3.us-east-1.amazonaws.com")
  spark.conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
  def main(args: Array[String]): Unit = {
    BronzeLayer.bronzeLayer(spark)
  }
}


