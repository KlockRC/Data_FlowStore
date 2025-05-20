package com.ruancesar.silver.model

import org.apache.spark.sql.types._


val dateSchema = StructType(Seq(
  StructField("date_key", IntegerType, false),
  StructField("date", DateType, false),
  StructField("year", IntegerType, false),
  StructField("month", IntegerType, false),
  StructField("month_name", StringType, false),
  StructField("day", IntegerType, false),
  StructField("day_of_week", IntegerType, false),
  StructField("day_name", StringType, false),
  StructField("week_of_year", IntegerType, false),
  StructField("quarter", IntegerType, false)
))