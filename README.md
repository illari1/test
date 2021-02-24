# test
Spring Boot app to maintain a subscription record about these addresses, disable the subscriptions and list out all the subscriptions/active/non active subscriptions that the app has.


1.- POST localhost:8080/operations/v1/subscribe   ---> subscribe endpoint, it will create a record subscription in DB with the online field set to true.
     Request body example:
         {
        "dlt": "Bitcoin",

        "addresses": [
               "www.greywuyr.com",
               "wwww.interesting.com" 
        ]
    }
    response body:
    {
    "online": true,
    "dlt": "Bitcoin",
    "addresses": [
        "www.greywuyr.com",
        "wwww.interesting.com"
    ]
}
2.- GET localhost:8080/operations/v1    ----> get all the subscriptions
  response example:
  
  [    {
          "id": "52d54d59-8994-42e1-90ff-4640de60c9e9",
          "online": false,
          "dlt": "Bitcoin",
          "addresses": [
              "www.greywuyr.com",
              "wwww.interesting.com"
          ]
      }
  ]

3.- PATCH localhost:8080/operations/v1/unsubscribe/52d54d59-8994-42e1-90ff-4640de60c9e9  ---> unsubscribe endpoint, it will update the field online to false 
in the record subscription with the id provided in the url.
