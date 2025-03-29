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
        }
    )
}