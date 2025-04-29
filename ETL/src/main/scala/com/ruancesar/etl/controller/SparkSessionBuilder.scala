package com.ruancesar.etl.controller

import org.apache.spark.sql.SparkSession



trait SparkSessionBuilder {
  def build(): SparkSession
  def confAwsRegionS3 (region: String): SparkSession
}

class SparkSessionBuilderImpl(appName: String, cluster: String) extends SparkSessionBuilder {
  private val sparkSession: SparkSession = SparkSession.builder()
    .appName(appName)
    .master(cluster)
    .getOrCreate()
  override def build(): SparkSession = sparkSession

  override def confAwsRegionS3 (region: String): SparkSession = {
    
    sparkSession.conf.set("spark.hadoop.fs.s3a.endpoint", s"s3.${region}.amazonaws.com")
    sparkSession.conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
    sparkSession
  }

}

object dado{
  val teste = new SparkSessionBuilderImpl("blabla", "teste")
  teste.confAwsRegionS3("us-east-1")
  val teste2 = new SparkSessionBuilderImpl("blebleble", "teste")
}