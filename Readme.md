# PHMS
[TOC]

All request use application/json

Authorization api need token in header such as

Authorization
```text
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGVuIiwiaWF0IjoxNjQ1NjMyMDI2LCJleHAiOjE2NDU3MTg0MjZ9.YySe0EQ1nTZ_UN1YqMwWJT5KXf3VK_KBDUO-QiprEUkqUpHdFbwHS7FWj2QUfnZ5dTSOGKevlQiR9TOxwCMdjg
```

## User
### Login
Url: 150.158.142.171:8080/api/auth/signin

Authorization: No

Method: Post

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
Url: 150.158.142.171:8080/api/auth/signup

Authorization: No

Method: POST

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

### Me
Url: 150.158.142.171:8080/api/user/me

Authorization: User

Method: Get

Response
```json
{
  "status": 200,
  "message": "success",
  "data": {
    "id": 3,
    "username": "chen",
    "email": "xbisatrouble@gmail.com",
    "password": "$2a$10$/PDQ/nbeT4l/NNTouPQ3Ru9D0rQ.fMHkcmLg9TPwBriIbFhVrLR46",
    "age": null,
    "gender": null,
    "weight": null,
    "height": null,
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
    ],
    "signsList": [],
    "doctor": null,
    "diet": null,
    "doctors": [],
    "medication": []
  },
  "timestamp": 1645632687221
}
```

## Vital Signs
### feed
Url: 150.158.142.171:8080/api/sign/feed

Authorization: User

Method: Get

Response
```json
{
    "status": 200,
    "message": "success",
    "data": [
        {
            "id": 4,
            "bloodPressure": "123",
            "glucoseLevel": "admin@123",
            "cholesterol": "123",
            "createdAt": "2022-02-24T17:48:05.000+00:00",
            "updatedAt": "2022-02-24T17:48:05.000+00:00",
            "user": {
                
            },
            "deleted": 0
        },
        {
        
        }
    ],
    "timestamp": 1645726159038
}
```

### All
Url: 150.158.142.171:8080/api/sign

Authorization: Admin

Method: Get

### GetOne
Url: 150.158.142.171:8080/api/sign/{id}

Authorization: User

Method: Get

```json
{
    "status": 400,
    "message": "wrong id or not authed",
    "data": null,
    "timestamp": 1645726570231
}
```

```json
{
    "status": 200,
    "message": "success",
    "data": {
        "id": 6,
        "bloodPressure": "123",
        "glucoseLevel": "admin@123",
        "cholesterol": "12311111",
        "createdAt": "2022-02-24T18:07:06.000+00:00",
        "updatedAt": "2022-02-24T18:07:06.000+00:00",
        "user": {

        },
        "deleted": 0
    },
    "timestamp": 1645726581406
}
```

### Create
Url: 150.158.142.171:8080/api/sign

Authorization: User

Method: Post

Request
```json
{
  "bloodPressure":"123",
  "glucoseLevel":"admin@123",
  "cholesterol":"12311111"
}
```
Response
```json
{
    "status":200,
    "message":"success",
    "data":{
        "id":9,
        "bloodPressure":"123",
        "glucoseLevel":"admin@123",
        "cholesterol":"12311111",
        "createdAt":"2022-02-24T18:18:26.398+00:00",
        "updatedAt":"2022-02-24T18:18:26.398+00:00",
        "user":{

        }
    },
    "timestamp":1645726706500
}
```

### Update
Url: 150.158.142.171:8080/api/sign

Authorization: User

Method: Put

Request
```json
{
  "bloodPressure":"123",
  "glucoseLevel":"admin@123",
  "cholesterol":"12311111"
}
```
Response
```json
{
    "status":200,
    "message":"success",
    "data":{
        "id":9,
        "bloodPressure":"123",
        "glucoseLevel":"admin@123",
        "cholesterol":"12311111",
        "createdAt":"2022-02-24T18:18:26.398+00:00",
        "updatedAt":"2022-02-24T18:18:26.398+00:00",
        "user":{

        }
    },
    "timestamp":1645726706500
}
```

### Delete
Url: 150.158.142.171:8080/api/sign/{id}

Authorization: User

Method: Delete

```json
{
    "status": 200,
    "message": "success",
    "data": "True",
    "timestamp": 1645726847300
}
```
