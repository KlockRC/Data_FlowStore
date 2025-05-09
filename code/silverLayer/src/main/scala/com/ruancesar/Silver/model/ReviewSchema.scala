package com.ruancesar.Silver.model

import org.apache.spark.sql.types._

val ReviewSchema: StructType = StructType(Seq(
  StructField("review_id", StringType, nullable = true),
  StructField("order_id", StringType, nullable = true),
  StructField("review_score", StringType, nullable = true),
  StructField("review_comment_title", StringType, nullable = true),
  StructField("review_comment_message", StringType, nullable = true),
  StructField("review_creation_date", StringType, nullable = true),
  StructField("review_answer_timestamp", StringType, nullable = true)
))