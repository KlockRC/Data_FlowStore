package api

import models.Produto
import scala.io.Source
import scala.util.Using


object csvreader {
    def lercsv(caminho: String): List[Produto] = {
        println(s"Lendo arquivo CSV de: $caminho")

        Using(Source.fromFile(caminho)) { file =>
            val linhas = file.getLines().drop(1).toList
            println(s"Número de linhas lidas (excluindo cabeçalho): ${linhas.size}")
            linhas.take(5).foreach(linha => println(s"Linha: $linha"))

            linhas.map { linha =>
                val colunas = linha.split(",").map(_.trim)
                try {
                    Produto(
                        colunas(0),
                        colunas(1),
                        colunas(2).toInt,
                        colunas(3).toInt,
                        colunas(4).toInt,
                        colunas(5).toInt,
                        colunas(6).toInt,
                        colunas(7).toInt,
                        colunas(8).toInt // Adicionado o novo campo
                    )
                } catch {
                    case e: NumberFormatException =>
                        println(s"Erro de conversão de número na linha: $linha. Erro: ${e.getMessage}")
                        null
                    case e: ArrayIndexOutOfBoundsException =>
                        println(s"Erro de índice fora dos limites na linha: $linha. Erro: ${e.getMessage}")
                        null
                }
            }.filter(_ != null)
        }.getOrElse {
            println(s"Erro ao ler o arquivo: $caminho")
            List.empty
        }
    }
}