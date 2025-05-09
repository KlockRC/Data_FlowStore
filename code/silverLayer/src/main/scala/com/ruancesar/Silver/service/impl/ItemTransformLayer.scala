package com.ruancesar.Silver.service.impl

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.*
import com.ruancesar.Silver.service.TransformLayer
import org.apache.spark.sql.types._


class ItemTransformLayer(df: DataFrame) extends TransformLayer{
  val dff = df.drop("partition")
    .withColumn("order_item_id", col("order_item_id").cast("int"))
    .withColumn("shipping_limit_date", to_timestamp(col("shipping_limit_date")))
    .withColumn("price", col("price").cast(DecimalType(10, 2)))
    .withColumn("freight_value", col("freight_value").cast(DecimalType(10, 2)))
  override def getValidRecords(): DataFrame = {
    dff.na.drop()
  }

  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("order_id").isNull ||
        col("order_item_id").isNull ||
        col("product_id").isNull ||
        col("seller_id").isNull ||
        col("shipping_limit_date").isNull ||
        col("price").isNull ||
        col("freight_value").isNull
    )
  }
}


