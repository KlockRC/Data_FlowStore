from main.infrastructure.impl.SparkSessionBuilderImpl import SparkSessionBuilderImpl
from main.adapter.impl.SparkReadImpl import SparkReadImpl
import main.model as model
from main.infrastructure.impl.SparkWriteSessionImpl import SparkWriteSessionImpl

####################################### TEST ###########################################

jdbc_url = "jdbc:postgresql://localhost:5432/Locals"
connection_properties = {
    "user": "Locals",
    "password": "Locals",
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
df_locals = SparkReadImpl(spark8).read_sql('"Locals"', jdbc_url, connection_properties)  # .show(truncate=False)
print("todos lidos")

def main():

    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_pedidos,"Pedidos")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_produtos,"Produtos")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_reviews,"Reviews")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_vendedores,"Vendedores")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_pagamentos,"Pagamentos")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_clientes,"Clientes")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_itens,"Itens")
    SparkWriteSessionImpl(jdbc_url,connection_properties).to_sql(df_locals,"Locals")
    print("todos gravados")


if __name__ == "__main__":
    main()