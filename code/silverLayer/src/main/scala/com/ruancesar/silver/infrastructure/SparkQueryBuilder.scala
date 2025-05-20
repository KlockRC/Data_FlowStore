package com.ruancesar.silver.infrastructure

import java.util.Properties
import scala.concurrent.Future

trait SparkQueryBuilder {
  def queryConsole(): Future[Unit]
  def queryParquet(): Future[Unit]
  def querySQL(url: String, table: String, prop: Properties, mode: String = "append"): Unit
}