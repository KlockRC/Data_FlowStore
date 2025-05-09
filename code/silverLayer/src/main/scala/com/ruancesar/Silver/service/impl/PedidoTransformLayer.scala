package com.ruancesar.Silver.service.impl

import com.ruancesar.Silver.service.TransformLayer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class PedidoTransformLayer(df: DataFrame) extends TransformLayer {
  val dff = df.withColumn("order_purchase_timestamp", to_timestamp(col("order_purchase_timestamp")))
    .withColumn("order_approved_at", to_timestamp(col("order_approved_at")))
    .withColumn("order_delivered_carrier_date", to_timestamp(col("order_delivered_carrier_date")))
    .withColumn("order_delivered_customer_date", to_timestamp(col("order_delivered_customer_date")))
    .withColumn("order_estimated_delivery_date", to_timestamp(col("order_estimated_delivery_date")))

  override def getValidRecords(): DataFrame = {
     dff.na.drop()
  }
  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("order_purchase_timestamp").isNull ||
      col("order_approved_at").isNull ||
      col("order_delivered_carrier_date").isNull ||
      col("order_delivered_customer_date").isNull ||
      col("order_estimated_delivery_date").isNull ||
      col("order_id").isNull ||
      col("order_status").isNull ||
      col("customer_id").isNull
    )
  }
  def getshipped(): DataFrame = {
    dff.filter(col("order_status") === "shipped")
  }

  def getcanceled(): DataFrame = {
    dff.filter(col("order_status") === "canceled")
  }

  def getunavailable(): DataFrame = {
    dff.filter(col("order_status") === "unavailable")
  }

  def getinvoiced(): DataFrame = {
    dff.filter(col("order_status") === "invoiced")
  }

  def getprocessing(): DataFrame = {
    dff.filter(col("order_status") === "processing")
  }

  def getcreated(): DataFrame = {
    dff.filter(col("order_status") === "created")
  }

  def getapproved(): DataFrame = {
    dff.filter(col("order_status") === "approved")
  }

}





