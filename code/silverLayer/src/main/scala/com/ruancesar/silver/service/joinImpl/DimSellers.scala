package com.ruancesar.silver.service.joinImpl

import com.ruancesar.silver.service.impl._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import com.ruancesar.silver.Main.df_local

object DimSellers {
  def join(df1: DataFrame): DataFrame = {
    val dimSellers = df1.withColumn("seller_key", monotonically_increasing_id())
      .join(df_local, df1("seller_zip_code_prefix") === df_local("geolocation_zip_code_prefix"), "left_outer")
      .select(
        col("seller_key"),
        col("seller_id"),
        col("seller_zip_code_prefix"),
        col("seller_city"),
        col("seller_state"),
        col("geolocation_city"),
        col("geolocation_state")
      )
    dimSellers
  }
}