package api

import akka.http.scaladsl.server.Directives._
import io.circe.syntax._
import akka.stream.scaladsl.Source
import akka.util.ByteString

object  Routes {
    def getRoutes = {
        path("produtos"){
            get {
                val Produtos = csvreader.lercsv("data/produtos.csv")
                complete(Produtos.asJson.noSpaces)
            }
        }
    }
}