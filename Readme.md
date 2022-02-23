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
  "status":200,
  "message":"success",
  "data":{
    "id":1,
    "username":"test",
    "email":"teset@gmail.com",
    "roles":[
      "ROLE_USER"
    ],
    "accessToken":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjQ1NTg3MjgwLCJleHAiOjE2NDU2NzM2ODB9.T4P8ILb4a8bFX0VgM0Xm-eDSQ8_wPKULY_lkwDw2beQiHVq3Yn8mWk2dqtUW67GxVeDApj1QHo339YQoR7Qunw",
    "tokenType":"Bearer"
  },
  "timestamp":1645587280512
}
```

### Registration
url: localhost:8080/api/auth/signup

method: POST

| Parma    | Type   | Description        | Required |
|----------|--------|--------------------|----------|
| username | string | user name          | True     |
| password | string | password           | True     |
| email    | string | email              | True     |
| role     | list   | role as admin/user | True     |
| gender   | string | gender             | False    |
| age      | int    | age                | False    |
| weight   | string | weight             | False    |
| height   | string | height             | False    |

Request:
```json
{
  "username":"test2",
  "password":"test123123",
  "email":"test@gmail.com",
  "role":[
    "user",
    "admin"
  ],
  "gender": "",
  "age": "",
  "weight": "",
  "height": ""
}
```
Response
```json
{
  "status": 200,
  "message": "success",
  "data": "User registered successfully!",
  "timestamp": 1645587380521
}
```