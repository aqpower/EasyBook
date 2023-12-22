---
title: EasyBook v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.17"

---

# EasyBook

> v1.0.0

Base URLs:

* <a href="localhost:8080">开发环境: localhost:8080</a>

* <a href="http://test-cn.your-api-server.com">测试环境: http://test-cn.your-api-server.com</a>

* <a href="http://prod-cn.your-api-server.com">正式环境: http://prod-cn.your-api-server.com</a>

# Authentication

# 用户

## POST 用户登录

POST /api/v1/login/users

用户登录接口。

> Body 请求参数

```json
{
  "id": "10000001",
  "email": "13358@emali.com",
  "password": "easybook123"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» email|body|string| 否 |none|
|» user_id|body|string| 否 |none|
|» password|body|string| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "code": 3,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5p2O5Y2OIiwiaWQiOjEwMDAwMDAwLCJleHAiOjE3MDIxNTE5ODV9.uFbbp6UVuriYC_1SAReptqRfemDmwNAFCZ3EO2SRbsI",
    "id": 10000000,
    "name": "李华"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» code|integer|true|none||none|
|»» token|string|true|none||用户令牌|
|»» id|integer|true|none||none|
|»» name|string|true|none||none|

## GET 邮箱验证

GET /api/v1/login/email-verification/

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|email|query|string| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": null
}
```

> 401 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {}
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|失败|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» verificationCode|string|true|none||邮箱验证码|

状态码 **401**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object¦null|true|none||none|

## POST 验证验证码

POST /api/v1/login/email-verification/

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|verfityCode|query|string| 否 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 用户注册

POST /api/v1/users

> Body 请求参数

```json
{
  "email": "13359@en=emali.com",
  "name": "测试用户6",
  "password": "eraybook123",
  "avatar": "6"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» user|body|object| 否 |none|
|»» avatar|body|integer| 是 |none|
|»» email|body|string| 是 |none|
|»» name|body|string| 是 |none|
|»» password|body|string| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": "注册成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|string|true|none||none|

## DELETE 用户注销接口

DELETE /api/v1/user

> Body 请求参数

```json
{
  "id": "10000004",
  "password": "easybook"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» id|body|integer| 是 |none|
|» password|body|string| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object¦null|true|none||none|

## PUT 用户修改信息接口

PUT /api/v1/user

> Body 请求参数

```json
{
  "id": "10000003",
  "name": "测试用户3"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» id|body|integer| 是 |none|
|» name|body|string| 否 |none|
|» avatar|body|integer| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## POST 用户拉黑接口

POST /api/v1/users/blackList

> Body 请求参数

```json
{
  "userId": "10000001",
  "blackUserId": "10000013"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» userId|body|integer| 是 |none|
|» balckuserId|body|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": "拉黑成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|

## POST 用户发帖

POST /api/v1/post

> Body 请求参数

```json
{
  "type": 1,
  "title": "voluptate",
  "contentText": "ut irure",
  "color": 4,
  "lyrics": null,
  "ip": "江西",
  "userId": 10000002,
  "urls": [
    "asdafdsdf",
    "asadsfewgdfg",
    "sadsafdhth"
  ]
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» type|body|integer| 是 |none|
|» tittle|body|string| 是 |none|
|» contentText|body|string| 是 |none|
|» color|body|integer| 是 |none|
|» lyrics|body|string¦null| 是 |none|
|» ip|body|string| 是 |none|
|» userId|body|integer| 是 |none|
|» urls|body|[string]| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object¦null|true|none||none|

## POST 用户发帖时上传图片

POST /api/v1/upload

> Body 请求参数

```yaml
image: file://C:\Users\lenovo\Pictures\QQ图片20231213160500.jpg

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» image|body|string(binary)| 否 |none|

> 返回示例

> 成功

```json
"\"code\": 200,\r\n    \"msg\": \"success\",\r\n    \"data\": [\r\n        \"https://easybook-image.oss-cn-beijing.aliyuncs.com/2593a4d8-97d3-4ff6-903a-4431049ebfe2.png\",\r\n        \"https://easybook-image.oss-cn-beijing.aliyuncs.com/2d1698c0-5c39-4e36-aba5-098da018d9e3.png\",\r\n        \"https://easybook-image.oss-cn-beijing.aliyuncs.com/27acd5d1-99f6-4594-a6a6-159d35c76bde.png\",\r\n        \"https://easybook-image.oss-cn-beijing.aliyuncs.com/63b2f88e-af57-4774-9f32-db720f25dbc4.png\"\r\n    ]\r\n}"
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[string]|true|none||none|

## GET 根据用户id查询用户所有帖子

GET /api/v1/posts/users/{userId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|path|string| 是 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "id": 0,
      "avatar": 0,
      "name": "string",
      "title": "string",
      "contentText": "string",
      "color": 0,
      "lyrics": "string",
      "url": null,
      "likeNum": 0,
      "collectionNum": 0,
      "ip": "string"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» id|integer|true|none||none|
|»» avatar|integer|true|none||none|
|»» name|string|true|none||none|
|»» title|string|true|none||none|
|»» contentText|string|true|none||none|
|»» color|integer|true|none||none|
|»» lyrics|string¦null|true|none||none|
|»» url|null|true|none||none|
|»» likeNum|integer|true|none||none|
|»» collectionNum|integer|true|none||none|
|»» ip|string|true|none||none|

## GET 用户主页信息展示

GET /api/v1/users/info/{userId}%2C{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|userId|path|string| 是 |none|
|id|path|string| 是 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "id": 0,
    "email": null,
    "name": "string",
    "password": null,
    "avatar": 0,
    "role": 0
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» id|integer|true|none||none|
|»» email|null|true|none||none|
|»» name|string|true|none||none|
|»» password|null|true|none||none|
|»» avatar|integer|true|none||none|
|»» role|integer|true|none||none|

## GET 查询用户通知

GET /api/v1/user/notify

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string| 否 |none|
|page|query|string| 否 |none|
|pageSize|query|string| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "id": 0,
      "postId": 0,
      "userId": 0,
      "content": "string"
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» id|integer|true|none||none|
|»» postId|integer|true|none||none|
|»» userId|integer|true|none||none|
|»» content|string|true|none||none|

## POST 用户关注接口

POST /api/v1/user/care

> Body 请求参数

```json
{
  "careUserId": 10000001,
  "caredUserId": 10000011
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» careUserId|body|integer| 是 |none|
|» caredUserId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## GET 查看用户的关注列表

GET /api/v1/user/care

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|integer| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "id": 0,
      "email": null,
      "name": "string",
      "password": null,
      "avatar": 0,
      "role": 0
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» id|integer|true|none||none|
|»» email|null|true|none||none|
|»» name|string|true|none||none|
|»» password|null|true|none||none|
|»» avatar|integer|true|none||none|
|»» role|integer|true|none||none|

## DELETE 用户取消关注

DELETE /api/v1/user/care

> Body 请求参数

```json
{
  "careUserId": 10000001,
  "caredUserId": 10000011
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» careUserId|body|integer| 是 |none|
|» caredUserId|body|integer| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## GET 查看用户粉丝列表

GET /api/v1/user/fans

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|integer| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "id": 0,
      "email": null,
      "name": "string",
      "password": null,
      "avatar": 0,
      "role": 0
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» id|integer|false|none||none|
|»» email|null|false|none||none|
|»» name|string|false|none||none|
|»» password|null|false|none||none|
|»» avatar|integer|false|none||none|
|»» role|integer|false|none||none|

## GET 用户点赞的帖子列表

GET /api/v1/posts/users/like

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|integer| 否 |none|
|page|query|integer| 否 |none|
|pageSize|query|integer| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "pageCount": 0,
    "posts": [
      {
        "id": 0,
        "userId": 0,
        "avatar": 0,
        "name": "string",
        "title": "string",
        "contentText": "string",
        "color": 0,
        "lyrics": null,
        "url": [
          "string"
        ],
        "likeNum": 0,
        "collectionNum": 0
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» pageCount|integer|true|none||none|
|»» posts|[object]|true|none||none|
|»»» id|integer|true|none||none|
|»»» userId|integer|true|none||none|
|»»» avatar|integer|true|none||none|
|»»» name|string|true|none||none|
|»»» title|string|true|none||none|
|»»» contentText|string|true|none||none|
|»»» color|integer|true|none||none|
|»»» lyrics|null|true|none||none|
|»»» url|[string]|true|none||none|
|»»» likeNum|integer|true|none||none|
|»»» collectionNum|integer|true|none||none|

## GET 用户收藏的帖子

GET /api/v1/posts/users/collection

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string| 否 |none|
|page|query|string| 否 |none|
|pageSize|query|string| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "pageCount": 0,
    "posts": [
      {
        "id": 0,
        "userId": 0,
        "avatar": 0,
        "name": "string",
        "title": "string",
        "contentText": "string",
        "color": 0,
        "lyrics": "string",
        "url": [
          "string"
        ],
        "likeNum": 0,
        "collectionNum": 0
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» pageCount|integer|true|none||none|
|»» posts|[object]|true|none||none|
|»»» id|integer|true|none||none|
|»»» userId|integer|true|none||none|
|»»» avatar|integer|true|none||none|
|»»» name|string|true|none||none|
|»»» title|string|true|none||none|
|»»» contentText|string|true|none||none|
|»»» color|integer|true|none||none|
|»»» lyrics|string¦null|true|none||none|
|»»» url|[string]|true|none||none|
|»»» likeNum|integer|true|none||none|
|»»» collectionNum|integer|true|none||none|

## GET 用户查看发出的全部评论

GET /api/v1/user/comments

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|string| 否 |none|
|page|query|string| 否 |none|
|pageSize|query|string| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "pageCount": 0,
    "comments": [
      {
        "id": 0,
        "userId": 0,
        "postId": 0,
        "content": "string",
        "commentTime": "string"
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» pageCount|integer|true|none||none|
|»» comments|[object]|true|none||none|
|»»» id|integer|true|none||none|
|»»» userId|integer|true|none||none|
|»»» postId|integer|true|none||none|
|»»» content|string|true|none||none|
|»»» commentTime|string|true|none||none|

## DELETE 用户取消拉黑

DELETE /api/v1/users/disBlackList

> Body 请求参数

```json
{
  "userId": "10000001",
  "blackUserId": "10000013"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

# 管理员

## POST 创建管理员

POST /api/v1/admins

> Body 请求参数

```json
{
  "adminId": 0,
  "admin": {
    "name": "string"
  }
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 是 |none|
|body|body|object| 否 |none|
|» adminId|body|integer| 是 |none|
|» admin|body|object| 是 |none|
|»» name|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": "200",
  "msg": "success",
  "data": {
    "password": "string"
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» password|string|true|none||none|

## POST 管理员登录

POST /api/v1/login/admins

这是用户管理员登录接口

> Body 请求参数

```json
{
  "id": 10014,
  "password": "57662696"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» id|body|integer| 是 |none|
|» password|body|string| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "code": 3,
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjpudWxsLCJpZCI6MTAwMTQsImV4cCI6MTcwMjI2Njc3Nn0.JXwZzTl_iPVSpiJv5dixS_pUAdhSCEWdXZZPOdvQbf0",
    "id": 10014,
    "name": "帅"
  }
}
```

```json
{
  "code": 401,
  "msg": "密码错误，请重新输入",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» code|integer|true|none||none|
|»» token|string|true|none||none|
|»» id|integer|true|none||none|
|»» name|string|true|none||none|

## DELETE 删除管理员

DELETE /api/v1/delete/admin

> Body 请求参数

```json
{
  "id": 0
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 是 |none|
|body|body|object| 否 |none|
|» id|body|integer| 是 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## GET 获取举报信息接口

GET /api/v1/login/reports

> Body 请求参数

```json
{}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|page|query|integer| 是 |none|
|pageSize|query|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "count": 4,
    "violation": [
      {
        "id": 1,
        "userId": 10000001,
        "postId": 2,
        "violationTime": "2023-12-13T13:48:20",
        "violationReason": "血腥暴力"
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|

## POST 处理举报信息接口

POST /api/v1/admin/operations

> Body 请求参数

```json
{
  "type": "2",
  "violationId": "15",
  "adminId": "10"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» type|body|string| 是 |none|
|» violationId|body|string| 是 |none|
|» adminId|body|string| 是 |none|

#### 枚举值

|属性|值|
|---|---|
|» type|1|
|» type|2|
|» type|3|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": "创建举报处理结果成功"
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|

## GET 获取今日发帖数量接口

GET /api/v1/posts/today/count

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 是 |none|

> 返回示例

> 成功

```json
{
  "count": 13
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» count|integer|true|none||none|

## GET 获取用户总数接口

GET /api/v1/count

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "count": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» count|integer|true|none||none|

## GET 获取帖子点击总数接口

GET /api/v1/visit

> 返回示例

> 200 Response

```json
{
  "count": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» count|integer|true|none||none|

## GET 查看最多使用的头像

GET /api/v1/user/avatar

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": [
    {
      "avatar": 0,
      "count": 0
    }
  ]
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|[object]|true|none||none|
|»» avatar|integer|true|none||none|
|»» count|integer|true|none||none|

# 帖子

## GET 用户进入系统展示所有帖子

GET /api/v1/posts

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|color|query|integer| 否 |none|
|text|query|string| 否 |none|
|id|query|integer| 是 |none|
|page|query|integer| 否 |none|
|pageSize|query|integer| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "count": 3,
    "postShows": [
      {
        "id": 2,
        "avatar": 1,
        "name": "杨柏",
        "title": "音乐",
        "contentText": "音乐分享",
        "color": 3,
        "lyrics": "你在台上唱着我的创作",
        "url": [
          "1123213",
          "21424"
        ],
        "likeNum": 0,
        "collectionNum": 0,
        "ip": "江西"
      },
      {
        "id": 3,
        "avatar": 1,
        "name": "杨柏",
        "title": "分享生活",
        "contentText": "写接口",
        "color": 3,
        "lyrics": null,
        "url": [],
        "likeNum": 0,
        "collectionNum": 0,
        "ip": "江西"
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|

## GET 根据帖子id查询帖子详情

GET /api/v1/posts/{postId}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|postId|path|string| 是 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "posts": {
      "id": 0,
      "avatar": 0,
      "name": "string",
      "title": "string",
      "contentText": "string",
      "color": 0,
      "lyrics": "string",
      "url": [
        "string"
      ],
      "likeNum": 0,
      "collectionNum": 0,
      "ip": "string"
    },
    "commentsList": [
      "string"
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» posts|object|true|none||none|
|»»» id|integer|true|none||none|
|»»» avatar|integer|true|none||none|
|»»» name|string|true|none||none|
|»»» title|string|true|none||none|
|»»» contentText|string|true|none||none|
|»»» color|integer|true|none||none|
|»»» lyrics|string|true|none||none|
|»»» url|[string]|true|none||none|
|»»» likeNum|integer|true|none||none|
|»»» collectionNum|integer|true|none||none|
|»»» ip|string|true|none||none|
|»» commentsList|[string]|true|none||none|

## POST 用户点赞帖子

POST /api/v1/posts/like

> Body 请求参数

```json
{
  "userId": "10000003",
  "postId": "4"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## POST 用户收藏帖子

POST /api/v1/posts/collection

> Body 请求参数

```json
{
  "userId": "10000003",
  "postId": "4"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## POST 用户评论帖子

POST /api/v1/posts/comment

> Body 请求参数

```json
{
  "userId": "10000003",
  "postId": "4",
  "content": "有趣"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## DELETE 删除帖子

DELETE /api/v1/posts/{id}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|path|integer| 是 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## POST 用户举报帖子

POST /api/v1/posts/violation

> Body 请求参数

```json
{
  "userId": "10000003",
  "violationReason": "涉嫌辱骂",
  "postId": "7"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|
|body|body|object| 否 |none|
|» userId|body|integer| 是 |none|
|» postId|body|integer| 是 |none|
|» violaionReason|body|string| 是 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": null
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|null|true|none||none|

## GET 关注的人的帖子

GET /api/v1/posts/care

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|id|query|integer| 否 |none|
|page|query|integer| 否 |none|
|pageSize|query|integer| 否 |none|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": {
    "pageCount": 0,
    "posts": [
      {
        "id": 0,
        "userId": 0,
        "avatar": 0,
        "name": "string",
        "title": "string",
        "contentText": "string",
        "color": 0,
        "lyrics": null,
        "url": [
          "string"
        ],
        "likeNum": 0,
        "collectionNum": 0
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|object|true|none||none|
|»» pageCount|integer|true|none||none|
|»» posts|[object]|true|none||none|
|»»» id|integer|true|none||none|
|»»» userId|integer|true|none||none|
|»»» avatar|integer|true|none||none|
|»»» name|string|true|none||none|
|»»» title|string|true|none||none|
|»»» contentText|string|true|none||none|
|»»» color|integer|true|none||none|
|»»» lyrics|null|true|none||none|
|»»» url|[string]|true|none||none|
|»»» likeNum|integer|true|none||none|
|»»» collectionNum|integer|true|none||none|

## GET 查看帖子总数

GET /api/v1/posts/all

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|Authorization|header|string| 否 |none|

> 返回示例

> 200 Response

```json
{
  "code": 0,
  "msg": "string",
  "data": 0
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» msg|string|true|none||none|
|» data|integer|true|none||none|

# 数据模型

<h2 id="tocS_Result">Result</h2>

<a id="schemaresult"></a>
<a id="schema_Result"></a>
<a id="tocSresult"></a>
<a id="tocsresult"></a>

```json
{
  "code": 0,
  "msg": "string",
  "data": {}
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|true|none||none|
|msg|string|true|none||none|
|data|object|true|none||none|

