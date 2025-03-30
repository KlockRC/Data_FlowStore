package api

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route.seal
import scala.concurrent.ExecutionContextExecutor
import io.circe.syntax._
import scala.io.StdIn
import api.Routes

object HttpServerRoutingMinimal {

def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    
    implicit val executionContext = system.executionContext

    val route = Routes.getRoutes

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server now online. Please navigate to\nhttp://localhost:8080/produtos\n" +
      s"http://localhost:8080/reviews\n" +
      s"http://localhost:8080/clientes\n" +
      s"http://localhost:8080/itens\n" +
      s"http://localhost:8080/local\n" +
      s"http://localhost:8080/pagamentos\n" +
      s"http://localhost:8080/pedidos\n" +
      s"http://localhost:8080/vendedores\n" +
      s"Press RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}