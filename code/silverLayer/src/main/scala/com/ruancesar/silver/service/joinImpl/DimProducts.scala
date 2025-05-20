package com.ruancesar.silver.service.joinImpl

import com.ruancesar.silver.service.impl._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object DimProducts {
  def join(df1: DataFrame): DataFrame = {
    val dimProducts = df1
      .withColumn("product_key", monotonically_increasing_id())
    dimProducts
  }
}