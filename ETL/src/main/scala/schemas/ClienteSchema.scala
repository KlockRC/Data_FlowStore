package schema

import org.apache.spark.sql.types._

val ClienteSchema = StructType(Seq(
  StructField("customer_unique_id", StringType, nullable = true),
  StructField("customer_state", StringType, nullable = true),
  StructField("customer_id", StringType, nullable = true),
  StructField("customer_zip_code_prefix", IntegerType, nullable = true),
  StructField("customer_state", StringType, nullable = true)
))

