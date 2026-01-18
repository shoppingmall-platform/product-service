# Coupon API

아래 내용은 현재 백엔드 구현 기준의 쿠폰 관련 요청/응답 형태입니다.

## 공통 헤더

- 관리자 API: `X-MEMBER-ID`, `ROLE: ADMIN`
- 회원 API: `X-MEMBER-ID`

## 1) 쿠폰 생성 (관리자)

- Endpoint: `POST /v1/coupons`

Request
```json
{
  "couponName": "string",
  "couponType": "FIXED | RATE",
  "amount": 5000,
  "minOrderPrice": 0,
  "maxDiscountPrice": 0,
  "couponStartDate": "YYYY-MM-DD",
  "couponEndDate": "YYYY-MM-DD",
  "issueType": "AUTO | CODE",
  "couponIssueCode": "string (issueType=CODE일 때 필요)",
  "issueTargetType": "ALL | MEMBERS",
  "issueTargetMemberIds": ["member1", "member2"],
  "applyType": "ALL_PRODUCTS | CATEGORY | TAG | PRODUCT",
  "applyTargetIds": [1, 2, 3],
  "comment": "string"
}
```

Response (201, body = 쿠폰 ID 문자열)
```text
"1"
```

## 2) 쿠폰 목록 검색 (관리자)

- Endpoint: `POST /v1/coupons/search`

Request
```json
{
  "couponName": "string | null",
  "couponStartDate": "YYYY-MM-DD | null",
  "couponEndDate": "YYYY-MM-DD | null"
}
```

Response
```json
[
  {
    "couponId": 1,
    "couponIssueCode": "string",
    "issueType": "AUTO | CODE",
    "issueTargetType": "ALL | MEMBERS",
    "couponName": "string",
    "couponType": "FIXED | RATE",
    "discountAmount": 5000,
    "minOrderPrice": 0,
    "maxDiscountPrice": 0,
    "couponStartDate": "YYYY-MM-DD | null",
    "couponEndDate": "YYYY-MM-DD | null",
    "comment": "string",
    "applyType": "ALL_PRODUCTS | CATEGORY | TAG | PRODUCT",
    "issueTargetMemberIds": ["member1", "member2"],
    "applyTargetIds": [1, 2, 3]
  }
]
```

## 3) 쿠폰 삭제 (관리자)

- Endpoint: `POST /v1/coupons/delete`

Request
```json
{ "couponId": 1 }
```

Response: `204 No Content`

## 4) 쿠폰 코드 발급 (회원)

- Endpoint: `POST /v1/coupons/issue`

Request
```json
{ "couponIssueCode": "string" }
```

Response (201)
```json
{ "memberCouponId": 1, "couponId": 1 }
```

유의사항
- `issueType=CODE` 쿠폰만 발급 가능

## 5) 보유 쿠폰 조회 (회원)

- Endpoint: `GET /v1/members/me/coupons`

Response
```json
[
  {
    "memberCouponId": 1,
    "couponIssueCode": "string",
    "couponName": "string",
    "couponType": "FIXED | RATE",
    "discountAmount": 5000,
    "minOrderPrice": 0,
    "maxDiscountPrice": 0,
    "issuedAt": "2025-01-01T00:00:00",
    "couponStartDate": "YYYY-MM-DD",
    "couponEndDate": "YYYY-MM-DD",
    "comment": "string",
    "status": "ACTIVE | USED | EXPIRED",
    "couponId": 1
  }
]
```
