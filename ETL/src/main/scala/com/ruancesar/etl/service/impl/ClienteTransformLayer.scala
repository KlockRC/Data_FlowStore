package com.ruancesar.etl.service.impl

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.*
import com.ruancesar.etl.service.TransformLayer


class ClienteTransformLayer(df: DataFrame) extends TransformLayer {

  override def transform(): DataFrame = {
    df.withColumn("timestamp_column", current_timestamp())
      .drop("partition")
      .withColumn("customer_zip_code_prefix", col("customer_zip_code_prefix").cast("int"))
      .withColumn("customer_city", regexp_replace(col("customer_city"), " ", "-"))
  }
}