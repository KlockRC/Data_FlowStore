package com.ruancesar.etl.service.impl

import com.ruancesar.etl.service.TransformLayer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class LocalTransformLayer(df: DataFrame) extends TransformLayer {
  val dff = df.withColumn("geolocation_zip_code_prefix", col("geolocation_zip_code_prefix").cast("int"))
    .withColumn("geolocation_lat",col("geolocation_lat").cast("double"))
    .withColumn("geolocation_lng",col("geolocation_lng").cast("double"))
  override def getValidRecords(): DataFrame = {
    dff.withColumn("geolocation_city", when(col("geolocation_city") === "sao paulo", "são paulo").otherwise(col("geolocation_city")))
      .na.drop()
  }

  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("geolocation_zip_code_prefix").isNull ||
      col("geolocation_lat").isNull ||
      col("geolocation_lng").isNull ||
      col("geolocation_city").isNull ||
      col("geolocation_state").isNull
    )
  }
}

