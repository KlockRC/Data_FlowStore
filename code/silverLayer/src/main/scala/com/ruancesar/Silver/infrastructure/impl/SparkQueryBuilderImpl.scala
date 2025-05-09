package com.ruancesar.Silver.infrastructure.impl

import com.ruancesar.Silver.infrastructure.SparkQueryBuilder
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.Trigger

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class SparkQueryBuilderImpl(dataframe: DataFrame, time: String) extends SparkQueryBuilder{
  override def queryConsole(): Future[Unit] = Future {
    dataframe.writeStream
      .format("console")
      .trigger(Trigger.ProcessingTime(time))
      .option("truncate", false)
      .start()
      .awaitTermination()
    ()
  }

  override def queryParquet(): Future[Unit] = Future {()}

  override def querySQL(): Future[Unit] = Future {()}

}
