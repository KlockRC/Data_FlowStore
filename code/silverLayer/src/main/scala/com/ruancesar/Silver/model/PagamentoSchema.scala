package com.ruancesar.Silver.model

import org.apache.spark.sql.types._

val PagamentoSchema: StructType = StructType(Seq(
  StructField("payment_type", StringType, nullable = true),
  StructField("payment_installments", StringType, nullable = true),
  StructField("payment_value", StringType, nullable = true),
  StructField("payment_sequential", StringType, nullable = true),
  StructField("order_id", StringType, nullable = true)
))

