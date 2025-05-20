package com.ruancesar.silver.adapter.impl

import com.ruancesar.silver.adapter.SparkRead
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.Properties

class SparkReadImpl(spark: SparkSession) extends SparkRead {
  override def readSql(url: String, table: String, prop: Properties): DataFrame = {
    spark
      .read.jdbc(url=url, table=table, properties=prop)
  }
}