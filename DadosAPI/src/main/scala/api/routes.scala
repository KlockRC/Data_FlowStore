package api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Source
import akka.util.ByteString
import io.circe.syntax._
import io.circe.generic.auto._ 
import api.csvreader


object Routes {
    def getRoutes: Route = concat( 
        path("produtos") {
            get {
                val produtos = csvreader.lerProdutos("data/produtos.csv")
                complete(produtos.asJson.noSpaces)
            }
        },
        path("reviews") {
            get {
                val reviews = csvreader.lerReviews ("data/reviews.csv")
                complete(reviews.asJson.noSpaces)
            }
        },
        path("clientes") {
            get {
                val clientes = csvreader.lerClientes ("data/clientes.csv")
                complete(clientes.asJson.noSpaces)
            }
        },
        path("itens") {
            get{
                val itens = csvreader.lerItens ("data/itens.csv")
                complete(itens.asJson.noSpaces)
            }
        },
        path("local") {
            get{
                val local = csvreader.lerLocal ("data/locals.csv")
                complete(local.asJson.noSpaces)
            }
        },
        path ("pagamentos") {
            get {
                val pagamentos = csvreader.lerPagamentos ("data/pagamentos.csv")
                complete(pagamentos.asJson.noSpaces)
            }
        },
        path ("pedidos") {
            get {
                val pedidos = csvreader.lerPedidos ("data/pedidos.csv")
                complete(pedidos.asJson.noSpaces)
            }
        },
        path ("vendedores") {
            get {
                var vendedores = csvreader.lerVendedores ("data/vendedores.csv")
                complete(vendedores.asJson.noSpaces)
            }
        }
    )
}