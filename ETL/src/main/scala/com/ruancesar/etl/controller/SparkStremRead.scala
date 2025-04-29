package com.ruancesar.etl.controller

import org.apache.spark.sql._
import org.apache.spark.sql.types._

trait SparkStreamRead {
  def readkafka(kakfa: String, topico: String, maxmensagem: String): DataFrame
  def reads3(format: String, maxfiles: String, s3: String, schema: StructType): DataFrame
}

class SparkStreamReadImpl(spark: SparkSession) extends SparkStreamRead {
  override def readkafka(kafka: String, topico: String, maxmensagem: String): DataFrame = {
      val teste = spark.readStream
        .format("kafka")
        .option("kafka.bootstrap.servers", kafka)
        .option("subscribe", topico)
        .option("maxOffsetsPerTrigger", maxmensagem)
        .load()
        .selectExpr("CAST(value AS STRING)")
      teste
  }

  override def reads3(format: String, maxfiles: String, s3: String, schema: StructType): DataFrame = {
    val teste2 = spark
      .readStream
      .schema(schema)
      .format(format)
      .option("maxFilesPerTrigger", maxfiles)
      .load(s3)
    teste2
  }
}
