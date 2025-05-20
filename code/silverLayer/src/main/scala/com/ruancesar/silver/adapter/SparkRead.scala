package com.ruancesar.silver.adapter

import org.apache.spark.sql.*

import java.util.Properties

trait SparkRead {
  def readSql(url: String, table: String, prop: Properties): DataFrame
}
