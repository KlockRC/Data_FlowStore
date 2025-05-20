package com.ruancesar.silver.service.joinImpl


import org.apache.spark.sql.{SparkSession, DataFrame, Row}
import org.apache.spark.sql.functions._
import com.ruancesar.silver.model.dateSchema


object DimDates {
  def join(df1: DataFrame, spark: SparkSession): DataFrame = {
    val minMaxDates = df1.select(
      min(to_date(col("order_purchase_timestamp"))).as("min_date"),
      max(to_date(col("order_delivered_customer_date"))).as("max_date")
    ).first()

    val dateRange = Seq.range(
      0,
      java.time.temporal.ChronoUnit.DAYS.between(
        minMaxDates.getAs[java.sql.Date]("min_date").toLocalDate,
        minMaxDates.getAs[java.sql.Date]("max_date").toLocalDate
      ).toInt + 1
    ).map { days =>
      java.sql.Date.valueOf(
        minMaxDates.getAs[java.sql.Date]("min_date").toLocalDate.plusDays(days)
      )
    }


    val dimDates = spark.createDataFrame(
      spark.sparkContext.parallelize(
        dateRange.map { date =>
          val localDate = date.toLocalDate
          Row(
            localDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd")).toInt,
            date,
            localDate.getYear,
            localDate.getMonthValue,
            localDate.getMonth.toString,
            localDate.getDayOfMonth,
            localDate.getDayOfWeek.getValue,
            localDate.getDayOfWeek.toString,
            localDate.get(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR),
            (localDate.getMonthValue - 1) / 3 + 1
          )
        }
      ),
      dateSchema
    )
    dimDates
  }
}