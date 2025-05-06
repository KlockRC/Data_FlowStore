package com.ruancesar.etl.service.impl

import com.ruancesar.etl.service.TransformLayer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class ProdutoTransformLayer(df: DataFrame) extends TransformLayer {
  private val dff = df
    .withColumn("product_name_lenght", col("product_name_lenght").cast("int"))
    .withColumn("product_description_lenght",col("product_description_lenght").cast("int"))
    .withColumn("product_photos_qty", col("product_photos_qty").cast("int"))
    .withColumn("product_weight_g", col("product_weight_g").cast("int"))
    .withColumn("product_length_cm", col("product_length_cm").cast("int"))
    .withColumn("product_height_cm",col("product_height_cm").cast("int"))
    .withColumn("product_width_cm",col("product_width_cm").cast("int"))

  override def getValidRecords(): DataFrame = {
    dff.na.drop()
  }

  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("product_id").isNull ||
        col("product_category_name").isNull ||
        col("product_name_lenght").isNull ||
        col("product_description_lenght").isNull ||
        col("product_photos_qty").isNull ||
        col("product_weight_g").isNull ||
        col("product_length_cm").isNull ||
        col("product_height_cm").isNull ||
        col("product_width_cm").isNull
    )
  }
}
