# Star Schema for E-commerce Data

This document outlines a star schema designed for the provided e-commerce datasets. The schema includes a central fact table (`fact_orders`) surrounded by several dimension tables.

---

## Fact Table: `fact_orders`

This table contains transactional information about orders.

| Column Name                       | Data Type     | Foreign Key To        | Description                                                              |
| :-------------------------------- | :------------ | :-------------------- | :----------------------------------------------------------------------- |
| `order_id`                        | NVARCHAR(50)  |                       | Unique order identifier                                                  |
| `customer_key`                    | INT           | `dim_customers`       | Foreign key to the customer dimension                                  |
| `product_key`                     | INT           | `dim_products`        | Foreign key to the product dimension                                   |
| `seller_key`                      | INT           | `dim_sellers`         | Foreign key to the seller dimension                                    |
| `payment_key`                     | INT           | `dim_order_payments`  | Foreign key to the payment information                                 |
| `review_key`                      | INT           | `dim_order_reviews`   | Foreign key to the review information                                  |
| `order_purchase_date_key`         | INT           | `dim_dates`           | Foreign key for the order purchase date                                |
| `order_approved_at_date_key`      | INT           | `dim_dates`           | Foreign key for the order approval date                                |
| `order_delivered_carrier_date_key`| INT           | `dim_dates`           | Foreign key for the date the order was delivered to the carrier        |
| `order_delivered_customer_date_key`| INT           | `dim_dates`           | Foreign key for the date the order was delivered to the customer       |
| `order_estimated_delivery_date_key`| INT           | `dim_dates`           | Foreign key for the customer-expected delivery date                      |
| `order_item_id`                   | INT           |                       | Sequential identifier of the order item                                |
| `shipping_limit_date`             | DATETIME      |                       | Limit date for seller to ship product                                  |
| `price`                           | FLOAT         |                       | Price of the item                                                      |
| `freight_value`                   | FLOAT         |                       | Item freight value paid by the customer                                |
| `order_status`                    | NVARCHAR(50)  |                       | Status of the order                                                    |

---

## Dimension Table: `dim_customers`

Contains information about the customers.

| Column Name                | Data Type        | Description                                                  |
| :------------------------- | :--------------- | :----------------------------------------------------------- |
| `customer_key`             | INT              | Surrogate key for customer                                   |
| `customer_id`              | NVARCHAR(50)     | Unique customer identifier                                   |
| `customer_unique_id`       | NVARCHAR(50)     | Customer reference across multiple orders                      |
| `customer_zip_code_prefix` | NVARCHAR(10)     | First 5 digits of customer zip code                          |
| `customer_city`            | NVARCHAR(50)     | Customer city name                                           |
| `customer_state`           | NVARCHAR(2)      | Customer state abbreviation                                  |
| `geolocation_city`         | NVARCHAR(50)     | City from geolocation data (if joined)                       |
| `geolocation_state`        | NVARCHAR(2)      | State from geolocation data (if joined)                      |

---

## Dimension Table: `dim_products`

Contains information about the products.

| Column Name                   | Data Type        | Description                                                      |
| :---------------------------- | :--------------- | :--------------------------------------------------------------- |
| `product_key`                 | INT              | Surrogate key for product                                       |
| `product_id`                  | NVARCHAR(50)     | Unique product identifier                                       |
| `product_category_name`       | NVARCHAR(100)    | Name of the product category                                    |
| `product_name_lenght`         | INT              | How many characters the product's name has                       |
| `product_description_lenght`  | INT              | How many characters the product's description has                |
| `product_photos_qty`          | INT              | Number of product photos available                              |
| `product_weight_g`            | INT              | Product weight in grams                                         |
| `product_length_cm`           | INT              | Product length in centimeters                                   |
| `product_height_cm`           | INT              | Product height in centimeters                                   |
| `product_width_cm`            | INT              | Product width in centimeters                                    |
| `product_category_name_english` | NVARCHAR(100)    | English translation of the product category name                |

---

## Dimension Table: `dim_sellers`

Contains information about the sellers.

| Column Name                | Data Type        | Description                                              |
| :------------------------- | :--------------- | :------------------------------------------------------- |
| `seller_key`             | INT              | Surrogate key for seller                                   |
| `seller_id`              | NVARCHAR(50)     | Unique seller identifier                                   |
| `seller_zip_code_prefix` | NVARCHAR(10)     | First 5 digits of seller zip code                          |
| `seller_city`            | NVARCHAR(50)     | Seller city name                                           |
| `seller_state`           | NVARCHAR(2)      | Seller state abbreviation                                  |
| `geolocation_city`         | NVARCHAR(50)     | City from geolocation data (if joined)                     |
| `geolocation_state`        | NVARCHAR(2)      | State from geolocation data (if joined)                    |

---

## Dimension Table: `dim_order_payments`

Contains information about how orders were paid.

| Column Name            | Data Type        | Description                                                |
| :--------------------- | :--------------- | :--------------------------------------------------------- |
| `payment_key`          | INT              | Surrogate key for payment                                  |
| `order_id`             | NVARCHAR(50)     | Order identifier                                           |
| `payment_sequential`   | INT              | Sequential number of payment for the same order            |
| `payment_type`         | NVARCHAR(50)     | Method of payment                                          |
| `payment_installments` | INT              | Number of installments chosen by the customer              |
| `payment_value`        | FLOAT            | Transaction value                                          |

---

## Dimension Table: `dim_order_reviews`

Contains information about customer reviews of orders.

| Column Name             | Data Type   | Description                                                              |
| :---------------------- | :---------- | :----------------------------------------------------------------------- |
| `review_key`          | INT         | Surrogate key for review                                                 |
| `review_id`             | NVARCHAR(50)| Unique review identifier                                                 |
| `order_id`              | NVARCHAR(50)| Order identifier                                                         |
| `review_score`          | INT         | Note given by the customer ranging from 1 to 5                           |
| `review_comment_title`  | TEXT        | Title of the review given by the customer                                |
| `review_comment_message`| TEXT        | Comment of the review given by the customer                              |
| `review_creation_date`  | DATE        | Shows the date when the evaluation was posted                           |
| `review_answer_timestamp`| TIMESTAMP   | Shows the date when the answer to the evaluation was posted              |

---

## Dimension Table: `dim_dates`

A central date dimension to link various dates.

| Column Name           | Data Type | Description                               |
| :-------------------- | :-------- | :---------------------------------------- |
| `date_key`            | INT       | Primary key in YYYYMMDD format            |
| `date`                | DATE      | Full date                                 |
| `year`                | INT       | Year                                      |
| `month`               | INT       | Month number (1-12)                       |
| `month_name`          | VARCHAR   | Month name                                |
| `day`                 | INT       | Day of the month                          |
| `day_of_week`         | INT       | Day of the week (e.g., 1 for Monday)      |
| `day_name`            | VARCHAR   | Day name                                  |
| `week_of_year`        | INT       | Week number of the year                   |
| `quarter`             | INT       | Quarter of the year (1-4)                 |

---

**Relationships:**

The `fact_orders` table has foreign key relationships with the primary keys of each dimension table (`dim_customers`, `dim_products`, `dim_sellers`, `dim_order_payments`, `dim_order_reviews`, and `dim_dates`).

