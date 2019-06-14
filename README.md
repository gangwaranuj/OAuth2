# demo-oauth2-spring-security

A simple Password Flow demo with Spring Security OAuth 2

## Testing

You can use [Postman](https://www.getpostman.com/) to test it.

### First you'll need to get the access_token

1. Create a POST request for the address: http://localhost:8080/oauth/token

2. You have to pass Basic Auth too, this is the client credentials, not the user. In this example the username is "client" and password "clientpassword" (without quotes).

3. Set the body

You'll have to choose x-www-form-urlencoded and set the values:

client_id   client

username    user

password    user

grant_type  password

![alt text](https://snag.gy/4kchzF.jpg)

When you hit the send button, you'll get something like that:


```json
{
    "access_token": "908217ea-e691-4d04-9e51-697939d60844",
    "token_type": "bearer",
    "refresh_token": "5da8341a-8f6b-4213-bddf-eaadd5a7b5e4",
    "expires_in": 119,
    "scope": "read write"
}
```

