package com.ruancesar.etl.infrastructure

import scala.concurrent.Future

trait SparkQueryBuilder {
  def queryConsole(): Future[Unit]
  def queryParquet(): Future[Unit]
  def querySQL(): Future[Unit]
}