package com.ruancesar.silver.service.impl

import com.ruancesar.silver.service.TransformLayer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class VendedorTransformLayer(df: DataFrame) extends TransformLayer{
  private val dff = df.withColumn("seller_zip_code_prefix",col("seller_zip_code_prefix").cast("int"))
  override def getValidRecords(): DataFrame = {
    dff.na.drop()
  }

  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("seller_id").isNull ||
        col("seller_zip_code_prefix").isNull ||
        col("seller_city").isNull ||
        col("seller_state").isNull
    )
  }

}

