from main.infrastructure.impl.SparkSessionBuilderImpl import SparkSessionBuilderImpl
from main.adapter.impl.SparkReadImpl import SparkReadImpl
import main.model as model
from main.infrastructure.impl.SparkWriteSessionImpl import SparkWriteSessionImpl

####################################### TEST ###########################################

url_raw = "jdbc:postgresql://localhost:5433/Locals"
con_raw = {
    "user": "Locals",
    "password": "Locals",
    "driver": "org.postgresql.Driver"
}

url_bronze = "jdbc:postgresql://localhost:5432/DW"
con_bronze = {
    "user": "Bronze",
    "password": "Bronze",
    "driver": "org.postgresql.Driver"
}


####################################### TEST ###########################################
# build Spark sessions for kafka
spark1 = SparkSessionBuilderImpl("teste", "local[*]").build()
spark2 = SparkSessionBuilderImpl("teste", "local[*]").build()
spark3 = SparkSessionBuilderImpl("teste", "local[*]").build()
spark4 = SparkSessionBuilderImpl("teste", "local[*]").build()

# build Spark sessions for s3
spark5 = SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
spark6 = SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")
spark7 = SparkSessionBuilderImpl("teste", "local[*]").confAwsRegionS3("us-east-1")

# build Spark sessions for SQL
spark8 = SparkSessionBuilderImpl("teste", "local[*]").build()
print("todos criados")
# Read Kafka Topics
df_pedidos = SparkReadImpl(spark1).read_kafka('localhost:9094', 'Topico-Pedido',
                                              model.PedidoSchema)  # .show(truncate=False)
df_produtos = SparkReadImpl(spark2).read_kafka('localhost:9094', 'Topico-Produto',
                                               model.ProdutoSchema)  # .show(truncate=False)
df_reviews = SparkReadImpl(spark3).read_kafka('localhost:9094', 'Topico-Review',
                                              model.ReviewSchema)  # .show(truncate=False)
df_vendedores = SparkReadImpl(spark4).read_kafka('localhost:9094', 'Topico-Vendedor',
                                                 model.VendedorSchema)  # .show(truncate=False)

# Read S3 Files
df_pagamentos = SparkReadImpl(spark5).read_s3("json",
                                              "s3a://rawlayer24042025/topics/Topico-Pagamento/")  # .show(truncate=False)
df_clientes = SparkReadImpl(spark6).read_s3("json",
                                            "s3a://rawlayer24042025/topics/Topico-Cliente/")  # .show(truncate=False)
df_itens = SparkReadImpl(spark7).read_s3("parquet", "s3a://rawlayer24042025/raw/itens/")  # .show(truncate=False)

# Read SQl Tables
df_locals = SparkReadImpl(spark8).read_sql('"Locals"', url_raw, con_raw)  # .show(truncate=False)
print("todos lidos")

def main():

    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_pedidos,"\"Bronze.Pedidos\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_produtos,"\"Bronze.Produtos\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_reviews,"\"Bronze.Reviews\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_vendedores,"\"Bronze.Vendedores\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_pagamentos,"\"Bronze.Pagamentos\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_clientes,"\"Bronze.Clientes\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_itens,"\"Bronze.Itens\"")
    SparkWriteSessionImpl(url_bronze,con_bronze).to_sql(df_locals,"\"Bronze.Locals\"")
    print("todos gravados")


if __name__ == "__main__":
    main()