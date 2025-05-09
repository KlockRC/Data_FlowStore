package com.ruancesar.etl.model

import org.apache.spark.sql.types
import org.apache.spark.sql.types.*

val PedidoSchema: StructType = StructType(Seq(
  StructField("order_id", StringType, nullable = true),
  StructField("customer_id", StringType, nullable = true),
  StructField("order_status", StringType, nullable = true),
  StructField("order_purchase_timestamp", StringType, nullable = true),
  StructField("order_approved_at", StringType, nullable = true),
  StructField("order_delivered_carrier_date", StringType, nullable = true),
  StructField("order_delivered_customer_date", StringType, nullable = true),
  StructField("order_estimated_delivery_date", StringType, nullable = true)
))
