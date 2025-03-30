package api
import models.Produto
import models.Review
import models.Cliente
import models.Item
import models.Local
import models.Pagamento
import models.Pedido
import models.Vendedor


import scala.io.Source
import scala.util.{Try, Using}
import scala.collection.StringView

object csvreader {
  def lerProdutos(caminho: String): List[Produto] = {
    Using(Source.fromFile(caminho)) { file =>
      val linhas = file.getLines().drop(1).toList
      println(s"Número de linhas lidas: ${linhas.size}")
      linhas.take(5).foreach(linha => println(s"Linha: $linha"))

      linhas.flatMap { linha =>
        val colunas = linha.split(",").map(_.trim)
        Try(Produto(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4), colunas(5), colunas(6), colunas(7), colunas(8))).toOption
      }
    }.getOrElse {
      println(s"Erro ao ler o arquivo: $caminho")
      List.empty
    }
  }
  def lerReviews(caminho: String): List[Review] = {
    Using(Source.fromFile(caminho)) { file =>
      val linhas = file.getLines().drop(1).toList
      println(s"numero de linhas lidas: ${linhas.size}")
      linhas.take(5).foreach(linha => println(s"Linha: $linha"))

      linhas.flatMap { linha => 
        val colunas = linha.split(",").map(_.trim)
        Try(Review(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4), colunas(5), colunas(6))).toOption
      }
    }.getOrElse {
      println(s"Erro ao ler o arquivo: $caminho")
      List.empty
    }
  }
  def lerClientes(caminho: String): List[Cliente] = {
     Using(Source.fromFile(caminho)) { file =>
      val linhas = file.getLines().drop(1).toList
      println(s"nemero de linhas lidas: ${linhas.size}")
      linhas.take(5).foreach(linha => println(s"linha: $linha"))

      linhas.flatMap { linha =>
        val colunas = linha.split(",").map(_.trim)
        Try(Cliente(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4))).toOption
        }
      }.getOrElse{
        println(s"Erro ao ler o arquivo: $caminho")
        List.empty
      }
    }
    def lerItens(caminho: String): List[Item] = {
      Using(Source.fromFile(caminho)) { file =>
       val linhas = file.getLines().drop(1).toList
       linhas.flatMap { linha =>
        val colunas = linha.split(",").map(_.trim)
        Try(Item(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4), colunas(5), colunas(6))).toOption
        } 
      }.getOrElse{
        println(s"Erro ao ler o arquivo: $caminho")
        List.empty
      }
    }
    def lerLocal(caminho: String): List[Local] = {
      Using(Source.fromFile(caminho)) { file =>
       val linhas = file.getLines().drop(1).toList
       linhas.flatMap { linha =>
        val colunas = linha.split(",").map(_.trim)
        Try(Local(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4))).toOption
        }
      }.getOrElse{
        println(s"Erro ao ler o arquivo: $caminho")
        List.empty
      }
    }
    def lerPagamentos(caminho: String): List[Pagamento] = {
      Using(Source.fromFile(caminho)) { file =>
        var linhas = file.getLines().drop(1).toList
        linhas.flatMap { linha =>
          val colunas = linha.split(",").map(_.trim)
          Try(Pagamento(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4))).toOption
          }  
      }.getOrElse{
        println(s"Erro ao ler o arquivo: $caminho")
        List.empty
      }
    }
    def lerPedidos(caminho: String): List[Pedido] = {
      Using(Source.fromFile(caminho)) { file =>
        var linhas = file.getLines().drop(1).toList
        linhas.flatMap { linha =>
          var colunas = linha.split(",").map(_.trim)
          Try(Pedido(colunas(0), colunas(1), colunas(2), colunas(3), colunas(4), colunas(5), colunas(6), colunas(7))).toOption
        }
      }.getOrElse {
        println(s"Erro ao ler o arquivo: $caminho")
        List.empty
      }
    }
    def lerVendedores(caminho: String): List[Vendedor] = {
      Using(Source.fromFile(caminho)) { file =>
        var linhas = file.getLines().drop(1).toList
        linhas.flatMap { linha =>
          var colunas = linha.split(",").map(_.trim)
          Try(Vendedor(colunas(0), colunas(1), colunas(2), colunas(3))).toOption
        }
      }.getOrElse {
        println(s"Erro ao ler o arquivo: $caminho")
        List.empty
      }
    }
  }