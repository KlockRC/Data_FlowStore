package com.ruancesar.silver.service.joinImpl

import com.ruancesar.silver.service.impl.*
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.*
import com.ruancesar.silver.Main.{df_item,df_pedido,dimCustomers,dimDates,dimOrderReviews,dimOrdersPayments,dimProducts,dimSellers}


object FactOrders {
  def join(): DataFrame = {
    val factOrders = df_pedido
      .join(df_item, "order_id")
      .join(dimCustomers, df_pedido("customer_id") === dimCustomers("customer_id"))
      .join(dimProducts, df_item("product_id") === dimProducts("product_id"))
      .join(dimSellers, df_item("seller_id") === dimSellers("seller_id"))
      .join(dimOrdersPayments, df_pedido("order_id") === dimOrdersPayments("order_id"), "left_outer")
      .join(dimOrderReviews, df_pedido("order_id") === dimOrderReviews("order_id"), "left_outer")
      .join(dimDates.as("dp"), to_date(df_pedido("order_purchase_timestamp")) === col("dp.date"), "left")
      .join(dimDates.as("da"), to_date(df_pedido("order_approved_at")) === col("da.date"), "left")
      .join(dimDates.as("dc"), to_date(df_pedido("order_delivered_carrier_date")) === col("dc.date"), "left")
      .join(dimDates.as("dd"), to_date(df_pedido("order_delivered_customer_date")) === col("dd.date"), "left")
      .join(dimDates.as("de"), to_date(df_pedido("order_estimated_delivery_date")) === col("de.date"), "left")
      .select(
        df_pedido("order_id"),
        dimCustomers("customer_key"),
        dimProducts("product_key"),
        dimSellers("seller_key"),
        dimOrdersPayments("payment_key"),
        dimOrderReviews("review_key"),
        col("dp.date_key").as("order_purchase_date_key"),
        col("da.date_key").as("order_approved_at_date_key"),
        col("dc.date_key").as("order_delivered_carrier_date_key"),
        col("dd.date_key").as("order_delivered_customer_date_key"),
        col("de.date_key").as("order_estimated_delivery_date_key"),
        df_item("order_item_id"),
        df_item("shipping_limit_date"),
        df_item("price"),
        df_item("freight_value"),
        df_pedido("order_status")
      )
    factOrders
  }
}