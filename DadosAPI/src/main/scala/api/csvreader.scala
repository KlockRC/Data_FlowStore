package api
import models.Produto
import models.Review

import scala.io.Source
import scala.util.{Try, Using}

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
}