package com.ruancesar.silver.service.joinImpl

import com.ruancesar.silver.service.impl._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import com.ruancesar.silver.Main.df_local


object DimCustomers {
  val geoDestrito = df_local.groupBy("geolocation_zip_code_prefix", "geolocation_city", "geolocation_state").agg(first("geolocation_lat").as("lat"),first("geolocation_lng").as("lng"))
  def join(df2: DataFrame): DataFrame = {
    val dimCustomers = df2.withColumn("customer_key", monotonically_increasing_id())
      .join(geoDestrito, df2("customer_zip_code_prefix") === geoDestrito("geolocation_zip_code_prefix"), "left_outer")
      .select(
        col("customer_key"),
        col("customer_id"),
        col("customer_unique_id"),
        col("customer_zip_code_prefix"),
        col("customer_city"),
        col("customer_state"),
        col("geolocation_city"),
        col("geolocation_state")
      )
    dimCustomers
  }
}
