import pandas as pd
from slugify import slugify
from sqlalchemy import create_engine
import time
import boto3
import io

def main():
    s3 = boto3.resource("s3")

    list = []

    for bucket in s3.buckets.all():
        list.append(bucket.name)
    s3bucket = list[0]
    con = create_engine("postgresql://Locals:Locals@localhost:5432/Locals")
    ID = 0

    chunks = pd.read_sql_table("Locals", con, index_col="geolocation_zip_code_prefix", chunksize=100000)
    for chunk in chunks:
        try:
            time1 =  time.time()
            df = chunk
            ID = ID + 1
            data = time.asctime()
            name = slugify(f"locals_{data}_id_{ID}")
            tempfile = io.BytesIO()
            df.to_parquet(tempfile, index=True)
            tempfile.seek(0)
            s3.Bucket(s3bucket).put_object(Key=tempfile"raw/{name}.parquet", Body=f)
            time2 = time.time()
            print("done %.3f" %(time2 - time1))
        except StopIteration:
            print("Fim")
            break
main()