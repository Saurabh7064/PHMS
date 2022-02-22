# PHMS

All request use application/json
## User
### Login
url: localhost:8080/api/auth/signin

method: POST 

| parma    | type   | description |
|----------|--------|-------------|
| username | string | user name   |
| password | string | password    |

Request:
```json
{
  "username":"chen",
  "password":"admin@123"
}
```
Response
```json
{
    "id": 3,
    "username": "chen",
    "email": "xbisatrouble@gmail.com",
    "roles": [
        "ROLE_USER"
    ],
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGVuIiwiaWF0IjoxNjQ1NTA4OTQzLCJleHAiOjE2NDU1OTUzNDN9.QdFa5bUf2lNLxTTPmVP5AFIq6F0-6FUxYfZC3orIZS10kaTDFsaetgDcGw8e0JMUe4mXhU6zXNpAjXvxFUYcgw",
    "tokenType": "Bearer"
}
```

### Registration
url: localhost:8080/api/auth/signin

method: POST

| parma    | type   | description |
|----------|--------|-------------|
| username | string | user name   |
| password | string | password    |

Request:
```json
{
  "username":"test2",
  "password":"test123123",
  "email":"test@gmail.com",
  "role":[
    "user",
    "admin"
  ]
}
```
Response
```json
{
  "message": "User registered successfully!"
}
```