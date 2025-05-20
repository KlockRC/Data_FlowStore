package com.ruancesar.silver.service.joinImpl

import com.ruancesar.silver.service.impl._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object DimOrderReviews {
  def join(df1: DataFrame): DataFrame = {
    val DimOrderReviews = df1
      .withColumn("review_key", monotonically_increasing_id())
    DimOrderReviews
  }
}