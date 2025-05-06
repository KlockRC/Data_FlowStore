package com.ruancesar.etl

import com.ruancesar.etl.adapter.impl.SparkStreamReadImpl
import com.ruancesar.etl.infrastructure.impl.{SparkSessionBuilderImpl, SparkQueryBuilderImpl}
import com.ruancesar.etl.model.*
import com.ruancesar.etl.service.impl._


object Main {

  val spark = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark2 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark3 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark4 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark5 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark6 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  
  val df_pedido_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Pedido", "1000", PedidoSchema)

  val df_vendedor_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Vendedor", "5", VendedorSchema)

  val df_review_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Review", "5", ReviewSchema)

  val df_produto_bronze = new SparkStreamReadImpl(spark)
    .readkafka("localhost:9094", "Topico-Produto", "5", ProdutoSchema)


  val df_pagamento_bronze = new SparkStreamReadImpl(spark2)
    .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Pagamento/", PagamentoSchema)

  val df_Item_bronze = new SparkStreamReadImpl(spark2)
    .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Item/", ItemSchema)
  

  val df_cliente_bronze = new SparkStreamReadImpl(spark2)
  .reads3("json", "1", "s3a://rawlayer24042025/topics/Topico-Cliente/", ClienteSchema)

  def main(args: Array[String]): Unit = {
    /*
    new SparkQueryBuilderImpl(df_produto_bronze, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df_pedido_bronze, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df_vendedor_bronze, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df_review_bronze, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df_pagamento_bronze, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df_Item_bronze, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df_cliente_bronze, "3 seconds").queryConsole()

     */

    new ClienteTransformLayer(df_cliente_bronze).getValidRecords()
    new ItemTransformLayer(df_Item_bronze).getValidRecords()
    new PagamentoTransformLayer(df_pagamento_bronze).getValidRecords()
    val teste = new PedidoTransformLayer(df_pedido_bronze).getInvalidRecords()
    new SparkQueryBuilderImpl(teste, "3 seconds").queryConsole()
    scala.io.StdIn.readLine()

  }
}