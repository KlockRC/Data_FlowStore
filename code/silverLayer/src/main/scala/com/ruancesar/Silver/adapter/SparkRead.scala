package com.ruancesar.Silver.adapter

import org.apache.spark.sql._

trait SparkRead {
  def reads3(format: String, s3: String): DataFrame
}
