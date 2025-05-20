package com.ruancesar.silver.infrastructure.impl

import com.ruancesar.silver.infrastructure.SparkQueryBuilder
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.Trigger
import java.util.Properties
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class SparkQueryBuilderImpl(dataframe: DataFrame, time: String = "2 seconds") extends SparkQueryBuilder{
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

  override def querySQL(url: String, table: String, prop: Properties, mode: String = "append"): Unit = {
    dataframe.write
      .mode(mode)
      .jdbc(url = url, table = table, connectionProperties = prop)

  }

}
