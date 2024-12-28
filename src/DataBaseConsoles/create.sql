CREATE TABLE users (
   id serial PRIMARY KEY,
   name VARCHAR (100) NOT NULL,
   email VARCHAR (255) UNIQUE NOT NULL
  )
  
  