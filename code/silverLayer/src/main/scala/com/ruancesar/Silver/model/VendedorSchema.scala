package com.ruancesar.Silver.model

import org.apache.spark.sql.types._

val VendedorSchema: StructType = StructType(Seq(
  StructField("seller_id", StringType, nullable = true),
  StructField("seller_zip_code_prefix", StringType, nullable = true),
  StructField("seller_city", StringType, nullable = true),
  StructField("seller_state", StringType, nullable = true)
))