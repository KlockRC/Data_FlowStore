package com.ruancesar.etl.service.impl

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.*
import com.ruancesar.etl.service.TransformLayer
import org.apache.spark.sql.types._


class PagamentoTransformLayer(df: DataFrame) extends TransformLayer {
  override def transform(): DataFrame = {
    df.drop("partition")
      .withColumn("payment_sequential",col("payment_sequential").cast("int"))
      .withColumn("payment_installments", col("payment_installments").cast("int"))
      .withColumn("payment_value",col("payment_value").cast(DecimalType(10,2)))
      .withColumn("payment_installments", when(col("payment_installments") === 0, 1).otherwise(col("payment_installments")))
  }

}
