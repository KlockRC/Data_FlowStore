package com.ruancesar.silver.service


import org.apache.spark.sql.DataFrame

trait TransformLayer {
  def getValidRecords(): DataFrame
  def getInvalidRecords(): DataFrame
}