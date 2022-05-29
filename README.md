# Getting Started

##Run application

````
* mvn clean install 
* docker compose up --build
* application will start at port 8080
````
##Usage
###Create contact: 

    curl --location --request POST 'http://localhost:8080/contacts' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name": "{name}",
    "email":"{email@gmail.com}"
    }'

###Get list of contacts: 
    
    curl --location --request GET 'http://localhost:8080/contacts' \
    --data-raw ''
