package com.ruancesar.silver.service.joinImpl

import com.ruancesar.silver.service.impl._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object DimOrdersPayments {
  def join(df1: DataFrame): DataFrame = {
    val dimOrderPaymensts = df1
      .withColumn("payment_key", monotonically_increasing_id())
    dimOrderPaymensts
  }
}