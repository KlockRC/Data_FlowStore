package com.ruancesar.etl.infrastructure

import org.apache.spark.sql.SparkSession

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
