package schema

import org.apache.spark.sql.types._

val ItemSchema = StructType(Seq(
  StructField("order_item_id", IntegerType, nullable = true),
  StructField("price", DoubleType, nullable = true),
  StructField("product_id", StringType, nullable = true),
  StructField("order_id", StringType, nullable = true),
  StructField("freight_value", DoubleType, nullable = true),
  StructField("seller_id", StringType, nullable = true),
  StructField("shipping_limit_date", DateType, nullable = true)
  
))
