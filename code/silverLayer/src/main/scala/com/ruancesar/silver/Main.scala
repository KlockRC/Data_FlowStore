package com.ruancesar.silver

import com.ruancesar.silver.adapter.impl.SparkReadImpl
import com.ruancesar.silver.infrastructure.impl.{SparkSessionBuilderImpl, SparkQueryBuilderImpl}
import com.ruancesar.silver.model.*
import com.ruancesar.silver.service.impl._
import com.ruancesar.silver.service.joinImpl._
import java.util.Properties
import com.ruancesar.silver.service.joinImpl.DimSellers
import com.ruancesar.silver.service.joinImpl.DimCustomers
import com.ruancesar.silver.service.joinImpl.DimDates
import com.ruancesar.silver.service.joinImpl.DimProducts
import com.ruancesar.silver.service.joinImpl.DimOrderReviews
import com.ruancesar.silver.service.joinImpl.DimOrdersPayments
import com.ruancesar.silver.service.joinImpl.FactOrders




object Main {

  //####################################### TEST ###########################################

  val url = "jdbc:postgresql://localhost:5432/Locals"
  val con = new Properties()
      con.put("user", "Locals")
      con.put("password", "Locals")
      con.put("driver", "org.postgresql.Driver")


 //####################################### TEST ###########################################

  val spark = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark2 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark3 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark4 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark5 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark6 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark7 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  val spark8 = new SparkSessionBuilderImpl("teste", "local[*]").build()
  
  val df_pedido_bronze = new SparkReadImpl(spark).readSql(url, "pedidos", con)

  val df_vendedor_bronze = new SparkReadImpl(spark2).readSql(url, "vendedores", con)

  val df_review_bronze = new SparkReadImpl(spark3).readSql(url, "reviews", con)

  val df_produto_bronze = new SparkReadImpl(spark4).readSql(url, "produtos", con)

  val df_pagamento_bronze = new SparkReadImpl(spark5).readSql(url, "pagamentos", con)

  val df_Item_bronze = new SparkReadImpl(spark6).readSql(url, "itens", con)

  val df_cliente_bronze = new SparkReadImpl(spark7).readSql(url, "clientes", con)

  val df_local_bronze = new SparkReadImpl(spark8).readSql(url, "locals", con)

  val df_cliente = new ClienteTransformLayer(df_cliente_bronze).getValidRecords()
  val df_item = new ItemTransformLayer(df_Item_bronze).getValidRecords()
  val df_pagamento = new PagamentoTransformLayer(df_pagamento_bronze).getValidRecords()
  val df_pedido = new PedidoTransformLayer(df_pedido_bronze).getValidRecords()
  val df_produto = new ProdutoTransformLayer(df_produto_bronze).getValidRecords()
  val df_review = new ReviewTransformLayer(df_review_bronze).getValidRecords()
  val df_local = new LocalTransformLayer(df_local_bronze).getValidRecords()
  val df_vendedor = new VendedorTransformLayer(df_vendedor_bronze).getValidRecords()
  println("passo1")
  val df_pagamentoteste = new PagamentoTransformLayer(df_pagamento_bronze).getInvalidRecords().show()

  ////////////////////////////////////////////////////////////////////////////////


  val dimCustomers = DimCustomers.join(df_cliente)
  val dimDates = DimDates.join(df_pedido, spark)
  val dimOrderReviews = DimOrderReviews.join(df_review)
  val dimOrdersPayments = DimOrdersPayments.join(df_pagamento)
  val dimProducts = DimProducts.join(df_produto)
  val dimSellers = DimSellers.join(df_vendedor)
  val factOrders = FactOrders.join().dropDuplicates()



  def main(args: Array[String]): Unit = {

    new SparkQueryBuilderImpl(factOrders).querySQL(url, "\"factOrders\"", con)
    new SparkQueryBuilderImpl(dimCustomers).querySQL(url,"\"dimCustomers\"", con)
    new SparkQueryBuilderImpl(dimDates).querySQL(url,"\"dimDates\"", con)
    new SparkQueryBuilderImpl(dimOrderReviews).querySQL(url, "\"dimOrderReviews\"", con)
    new SparkQueryBuilderImpl(dimOrdersPayments).querySQL(url, "\"dimOrdersPayments\"", con)
    new SparkQueryBuilderImpl(dimProducts).querySQL(url, "\"dimProducts\"", con)
    new SparkQueryBuilderImpl(dimSellers).querySQL(url, "\"dimSellers\"", con)

  }
}