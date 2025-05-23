package com.ruancesar.silver

import com.ruancesar.silver.adapter.impl.SparkReadImpl
import com.ruancesar.silver.infrastructure.impl.{SparkSessionBuilderImpl, SparkQueryBuilderImpl}
import com.ruancesar.silver.service.impl._
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

  val url = "jdbc:postgresql://localhost:5432/DW"
  val con = new Properties()
      con.put("user", "Bronze")
      con.put("password", "Bronze")
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
  
  val df_pedido_bronze = new SparkReadImpl(spark).readSql(url, "\"Bronze.Pedidos\"", con)

  val df_vendedor_bronze = new SparkReadImpl(spark2).readSql(url, "\"Bronze.Vendedores\"", con)

  val df_review_bronze = new SparkReadImpl(spark3).readSql(url, "\"Bronze.Reviews\"", con)

  val df_produto_bronze = new SparkReadImpl(spark4).readSql(url, "\"Bronze.Produtos\"", con)

  val df_pagamento_bronze = new SparkReadImpl(spark5).readSql(url, "\"Bronze.Pagamentos\"", con)

  val df_Item_bronze = new SparkReadImpl(spark6).readSql(url, "\"Bronze.Itens\"", con)

  val df_cliente_bronze = new SparkReadImpl(spark7).readSql(url, "\"Bronze.Clientes\"", con)

  val df_local_bronze = new SparkReadImpl(spark8).readSql(url, "\"Bronze.Locals\"", con)

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

    new SparkQueryBuilderImpl(factOrders).querySQL(url, "\"Silver.factOrders\"", con)
    new SparkQueryBuilderImpl(dimCustomers).querySQL(url,"\"Silver.dimCustomers\"", con)
    new SparkQueryBuilderImpl(dimDates).querySQL(url,"\"Silver.dimDates\"", con)
    new SparkQueryBuilderImpl(dimOrderReviews).querySQL(url, "\"Silver.dimOrderReviews\"", con)
    new SparkQueryBuilderImpl(dimOrdersPayments).querySQL(url, "\"Silver.dimOrdersPayments\"", con)
    new SparkQueryBuilderImpl(dimProducts).querySQL(url, "\"Silver.dimProducts\"", con)
    new SparkQueryBuilderImpl(dimSellers).querySQL(url, "\"Silver.dimSellers\"", con)

  }
}