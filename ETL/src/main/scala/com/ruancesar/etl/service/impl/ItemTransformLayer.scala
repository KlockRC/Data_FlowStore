package com.ruancesar.etl.service.impl

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.*
import com.ruancesar.etl.service.TransformLayer
import org.apache.spark.sql.types._


class ItemTransformLayer(df: DataFrame) extends TransformLayer{
  override def transform(): DataFrame = {
    df.drop("partition")
      .withColumn("order_item_id", col("order_item_id").cast("int"))
      .withColumn("shipping_limit_date", to_timestamp(col("shipping_limit_date")))
      .withColumn("price",col("price").cast(DecimalType(10,2)))
      .withColumn("freight_value",col("freight_value").cast(DecimalType(10,2)))
  }
}
