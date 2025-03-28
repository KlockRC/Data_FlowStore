package api

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route.seal
import scala.concurrent.ExecutionContextExecutor
import io.circe.syntax._ // Added import for .asJson
import scala.io.StdIn

object HttpServerRoutingMinimal {

def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    
    implicit val executionContext = system.executionContext

    val route = path("produtos") {
        get {
        val produtos = csvreader.lercsv("data/produtos.csv")
            complete(produtos.asJson.noSpaces)
    }
  }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server now online. Please navigate to http://localhost:8080/hello\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}