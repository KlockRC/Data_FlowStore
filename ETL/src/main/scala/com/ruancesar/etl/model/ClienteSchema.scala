package com.ruancesar.etl.model

import org.apache.spark.sql.types._

val ClienteSchema: StructType = StructType(Seq(
  StructField("customer_unique_id", StringType, nullable = true),
  StructField("customer_state", StringType, nullable = true),
  StructField("customer_id", StringType, nullable = true),
  StructField("customer_zip_code_prefix", StringType, nullable = true),
  StructField("customer_state", StringType, nullable = true)
))