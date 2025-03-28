package api
import models.Produto
import scala.io.Source
import scala.util.{Try, Using}

object csvreader {
  def lercsv(caminho: String): List[Produto] = {
    println(s"Lendo arquivo CSV de: $caminho")

    Using(Source.fromFile(caminho)) { file =>
      val linhas = file.getLines().drop(1).toList
      println(s"Número de linhas lidas (excluindo cabeçalho): ${linhas.size}")
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
}