package com.ruancesar.Silver

import com.ruancesar.Silver.adapter.impl.{SparkStreamReadImpl,SparkReadImpl}
import com.ruancesar.Silver.infrastructure.impl.{SparkSessionBuilderImpl, SparkQueryBuilderImpl}
import com.ruancesar.Silver.model.*
import com.ruancesar.Silver.service.impl._
import com.ruancesar.Silver.service.joinImpl._

object Main {

  val spark = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark2 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark3 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark4 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark5 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark6 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  val spark7 = new SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
  
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

  val df_local_bronze = new SparkReadImpl(spark7).reads3("parquet", "s3a://rawlayer24042025/raw/")

  def main(args: Array[String]): Unit = {

    val df1 = new ClienteTransformLayer(df_cliente_bronze).getValidRecords()
    val df2 = new ItemTransformLayer(df_Item_bronze).getValidRecords()
    val df3 = new PagamentoTransformLayer(df_pagamento_bronze).getValidRecords()
    val df4 = new PedidoTransformLayer(df_pedido_bronze).getValidRecords()
    val df5 = new ProdutoTransformLayer(df_produto_bronze).getValidRecords()
    val df6 = new ReviewTransformLayer(df_review_bronze).getValidRecords()
    val df7 = new LocalTransformLayer(df_local_bronze).getValidRecords() // lido apenas uma vez usar .show() e nao o SparkQueryBuilderImpl NAO É UM STREAM
    val df8 = new VendedorTransformLayer(df_vendedor_bronze).getValidRecords()
/*
    new SparkQueryBuilderImpl(df1, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df2, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df3, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df4, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df5, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df6, "3 seconds").queryConsole()
    new SparkQueryBuilderImpl(df8, "3 seconds").queryConsole()

 */
    val teste = new JoinPedidoClientePagamento(df3, df4).join()

    new SparkQueryBuilderImpl(teste, "3 seconds").queryConsole()


    scala.io.StdIn.readLine()

  }
}