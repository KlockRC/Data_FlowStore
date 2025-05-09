package com.ruancesar.Silver.adapter

import org.apache.spark.sql._
import org.apache.spark.sql.types._

trait SparkStreamRead {
  def readkafka(kakfa: String, topico: String, maxmensagem: String, schema: StructType): DataFrame
  def reads3(format: String, maxfiles: String, s3: String, schema: StructType): DataFrame
}

