package etl

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import schema.{ClienteSchema, ItemSchema, PagamentoSchema}


object BronzeLayer {
  def bronzeLayer(spark: SparkSession): Unit = {


    val df_cliente_bronze = spark.readStream
      .schema(ClienteSchema)
      .format("json")
      .option("maxFilesPerTrigger", "1")
      .load("s3a://rawlayer24042025/topics/Topico-Cliente/")

    val df_item_bronze = spark.readStream
      .schema(ItemSchema)
      .format("json")
      .option("maxFilesPerTrigger", "1")
      .load("s3a://rawlayer24042025/topics/Topico-Item/")


    val df_pagamento_bronze = spark.readStream
      .schema(PagamentoSchema)
      .format("json")
      .option("maxFilesPerTrigger", "1")
      .load("s3a://rawlayer24042025/topics/Topico-Pagamento/")


    val df_pedido_bronze = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9094")
      .option("subscribe", "Topico-Pedido")
      .option("maxOffsetsPerTrigger", "5")
      .load()
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")


    val df_produto_bronze = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9094")
      .option("subscribe","Topico-Produto")
      .option("maxOffsetsPerTrigger", "5")
      .load()
      .selectExpr("Cast(value AS STRING)")


    val df_review_bronze = spark.readStream
      .format("kafka")
      .options(Map(
        "kafka.bootstrap.servers"->"localhost:9094",
        "subscribe"->"Topico-Review",
        "maxOffsetsPerTrigger"->"5"
      ))
      .load()
      .selectExpr("Cast(value AS STRING)")
/*
    val query = df_review_bronze
      .writeStream
      .format("console").
      trigger(Trigger.ProcessingTime("3 seconds"))
      .start()
    query.awaitTermination()

 */



  }
}