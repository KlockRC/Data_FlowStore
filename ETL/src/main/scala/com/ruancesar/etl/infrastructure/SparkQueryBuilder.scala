package com.ruancesar.etl.infrastructure

import org.apache.spark.sql.DataFrame
import scala.concurrent.Future

trait SparkQueryBuilder {
  def queryConsole(): Future[Unit]
  def queryParquet(): Future[Unit]
  def querySQL(): Future[Unit]
}