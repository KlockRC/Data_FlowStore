package com.ruancesar.etl.service.impl

import com.ruancesar.etl.service.TransformLayer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class ReviewTransformLayer(df: DataFrame) extends TransformLayer {
  private val dff = df
    .withColumn("review_score",col("review_score").cast("int"))
    .withColumn("review_creation_date", to_timestamp(col("review_creation_date")))
    .withColumn("review_answer_timestamp",to_timestamp(col("review_answer_timestamp")))
    .withColumn("review_comment_message", when(col("review_comment_message") === "nan", "").otherwise(col("review_comment_message")))


  override def getValidRecords(): DataFrame = {
    dff.filter(
      col("review_id").isNotNull ||
      col("order_id").isNotNull ||
      col("review_score").isNotNull ||
      col("review_creation_date").isNotNull ||
      col("review_answer_timestamp").isNotNull
    )
  }

  override def getInvalidRecords(): DataFrame = {
    dff.filter(
      col("review_id").isNull ||
      col("order_id").isNull ||
      col("review_score").isNull ||
      col("review_creation_date").isNull ||
      col("review_answer_timestamp").isNull
    )
  }

}
