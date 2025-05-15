import pandas as pd
from sqlalchemy import create_engine
from time import time

con = create_engine("postgresql://Locals:Locals@localhost:5432/Locals")
chunk = pd.read_csv("olist_geolocation_dataset.csv", chunksize = 7000)

while True:
    try:
        time1 = time()
        df = next(chunk)
        df.to_sql(name="Locals", con=con, if_exists="append", index=False)
        time2 = time()
        print("done %.3f" %(time2 - time1))
    except StopIteration:
        print("fim")
        break