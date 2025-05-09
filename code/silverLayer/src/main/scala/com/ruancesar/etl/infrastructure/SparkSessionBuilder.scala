package com.ruancesar.etl.infrastructure


import org.apache.spark.sql.SparkSession


trait SparkSessionBuilder {
  def build(): SparkSession
  def confAwsRegionS3 (region: String): SparkSession
}
