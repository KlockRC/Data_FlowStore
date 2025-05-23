import pandas as pd
from slugify import slugify
from sqlalchemy import create_engine
import time
import boto3
import io
import time

con0 = create_engine("postgresql://Locals:Locals@localhost:5433/Locals")


def LocalsSQL():
    chunk_Locals = pd.read_csv("olist_geolocation_dataset.csv", chunksize = 7000)
    while True:
        try:
            time1 = time.time()
            df = next(chunk_Locals)
            df.to_sql(name="Locals", con=con0, if_exists="append", index=False)
            time2 = time.time()
            print("done %.3f" %(time2 - time1))
        except StopIteration:
            print("fim Local SQL")
            break

def Itens_Parquet_S3():

    s3 = boto3.resource("s3")

    list = []

    for bucket in s3.buckets.all():
        list.append(bucket.name)
    s3bucket = list[0]
    ID = 0

    chunks = pd.read_csv("olist_order_items_dataset.csv", chunksize=7000)
    for chunk in chunks:
        try:
            time1 =  time.time()
            df = chunk
            ID = ID + 1
            data = time.asctime()
            name = slugify(f"Itens_{data}_id_{ID}")
            tempfile = io.BytesIO()
            df.to_parquet(tempfile, index=True)
            tempfile.seek(0)
            s3.Bucket(s3bucket).put_object(Key=f"raw/itens/{name}.parquet", Body=tempfile)
            time2 = time.time()
            print("done %.3f" %(time2 - time1))
        except StopIteration:
            print("fim Itens Parquet")
            break

LocalsSQL()
Itens_Parquet_S3()
