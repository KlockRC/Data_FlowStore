package com.ruancesar.Silver.model

import org.apache.spark.sql.types._

val ProdutoSchema: StructType = StructType(Seq(
  StructField("product_id", StringType, nullable = true),
  StructField("product_category_name", StringType, nullable = true),
  StructField("product_name_lenght", StringType, nullable = true),
  StructField("product_description_lenght", StringType, nullable = true),
  StructField("product_photos_qty", StringType, nullable = true),
  StructField("product_weight_g", StringType, nullable = true),
  StructField("product_length_cm", StringType, nullable = true),
  StructField("product_height_cm", StringType, nullable = true),
  StructField("product_width_cm", StringType, nullable = true)
))
