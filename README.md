# Getting Started

##Run application

````
* mvn clean install 
* docker compose up --build
* application will start at port 8080
````
##Usage

###Variables
* channel types: INTERNAL, GOOGLE, WHATS_APP;
* email: {random}@gmail.com
* name: {random}

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

###Send message:
    
    curl --location --request POST 'http://localhost:8080/messages' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name": "{name}",
    "email": "{email}",
    "channelType": "INTERNAL",
    "text" : "hello ${name} ${email} ${BitCoin}"
    }'

###Get list of messages:
    
    curl --location --request GET 'http://localhost:8080/messages/{email}

###Receive webhook message
    
    curl --location --request POST 'http://localhost:8080/webhook/messages' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name": "{name}",
    "email": "{email}",
    "channelType": "WHATS_APP",
    "text" : "hello ${name} ${BitCoin}"
    }'