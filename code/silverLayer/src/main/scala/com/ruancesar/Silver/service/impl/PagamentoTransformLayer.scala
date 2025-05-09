package com.ruancesar.Silver.service.impl

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.*
import com.ruancesar.Silver.service.TransformLayer
import org.apache.spark.sql.types._


class PagamentoTransformLayer(df: DataFrame) extends TransformLayer {
  val dff = df.drop("partition")
    .withColumn("payment_sequential", col("payment_sequential").cast("int"))
    .withColumn("payment_installments", col("payment_installments").cast("int"))
    .withColumn("payment_value", col("payment_value").cast(DecimalType(10, 2)))
    .withColumn("payment_installments", when(col("payment_installments") === 0, 1).otherwise(col("payment_installments")))
    .na.drop()
  override def getValidRecords(): DataFrame = {
    dff.na.drop()
  }

  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("order_id").isNull ||
        col("payment_sequential").isNull ||
        col("payment_type").isNull ||
        col("payment_installments").isNull ||
        col("payment_value").isNull
    )
  }
}
