package com.ruancesar.Silver.model

import org.apache.spark.sql.types._

val ItemSchema: StructType = StructType(Seq(
  StructField("order_item_id", StringType, nullable = true),
  StructField("price", StringType, nullable = true),
  StructField("product_id", StringType, nullable = true),
  StructField("order_id", StringType, nullable = true),
  StructField("freight_value", StringType, nullable = true),
  StructField("seller_id", StringType, nullable = true),
  StructField("shipping_limit_date", DateType, nullable = true)

))
