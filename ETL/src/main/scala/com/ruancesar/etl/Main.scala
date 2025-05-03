package com.ruancesar.etl

import com.ruancesar.etl.adapter.impl.SparkStreamReadImpl
import com.ruancesar.etl.infrastructure.SparkSessionBuilderImpl
import com.ruancesar.etl.model.*
import com.ruancesar.etl.service.impl._
import org.apache.spark.sql.streaming.Trigger


object Main {

  val spark = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark2 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark3 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark4 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark5 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark6 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")

/*
  val df_pedido_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Pedido", "5")

  val df_vendedor_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Vendedor", "5")

  val df_review_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Review", "5")

  val df_produto_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Produto", "5")


  val df_pagamento_bronze = new SparkStreamReadImpl(spark2)
    .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Pagamento/", PagamentoSchema)

  val df_Item_bronze = new SparkStreamReadImpl(spark2)
    .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Item/", ItemSchema)

  val df_cliente_bronze = new SparkStreamReadImpl(spark2)
    .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Cliente/", ClienteSchema)

 */
  val df_cliente_bronze = new SparkStreamReadImpl(spark2)
  .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Cliente/", ClienteSchema)

  def main(args: Array[String]): Unit = {






  }
}