# 📚 Library Management API

This project provides a simple Library Management REST API built with **Spring Boot**.

## 🚀 Base URL
```
http://localhost:8080/
```

---

## 📖 BookController (`/api/books`)

### 1. Get all books
- **Endpoint**: `GET /api/books`
- **Description**: Fetch all books.
- **Response**: List of books.

### 2. Get book by ID
- **Endpoint**: `GET /api/books/{bookId}`
- **Path Variable**: `bookId` *(String)*
- **Response**: Book details.

### 3. Get available books
- **Endpoint**: `GET /api/books/available`
- **Response**: List of available books.

### 4. Get borrowed books
- **Endpoint**: `GET /api/books/borrowed`
- **Response**: List of borrowed books.

### 5. Update book (or mark deleted)
- **Endpoint**: `PUT /api/books/{bookId}`
- **Path Variable**: `bookId` *(String)*
- **Description**: Update book details or mark for deletion.

### 6. Delete book
- **Endpoint**: `DELETE /api/books/{bookId}`
- **Path Variable**: `bookId` *(String)*
- **Description**: Remove a book from the library.

### 7. Search books
- **Endpoint**: `GET /api/books/search`
- **Query Params**: (e.g., `title`, `author`)
- **Response**: Matching books.

---

## 🏛️ LibraryController (`/api/library`)

### 1. Borrow book
- **Endpoint**: `POST /api/library/borrow`
- **Body**:
```json
{
  "memberId": 1,
  "bookId": "ABC123"
}
```
- **Response**: Confirmation of borrowing.

### 2. Return book
- **Endpoint**: `POST /api/library/return`
- **Body**:
```json
{
  "memberId": 1,
  "bookId": "ABC123"
}
```
- **Response**: Confirmation of return.

---

## 👤 MemberController (`/api/members`)

### 1. Get all members
- **Endpoint**: `GET /api/members`
- **Response**: List of members.

### 2. Get member by ID
- **Endpoint**: `GET /api/members/{memberId}`
- **Path Variable**: `memberId` *(Long)*
- **Response**: Member details.

### 3. Get member details
- **Endpoint**: `GET /api/members/{memberId}/details`
- **Path Variable**: `memberId` *(Long)*
- **Response**: Detailed info about a member.

### 4. Update member (or mark deleted)
- **Endpoint**: `PUT /api/members/{memberId}`
- **Path Variable**: `memberId` *(Long)*
- **Description**: Update member details or mark for deletion.

### 5. Delete member
- **Endpoint**: `DELETE /api/members/{memberId}`
- **Path Variable**: `memberId` *(Long)*
- **Description**: Remove a member from the system.

### 6. Search members
- **Endpoint**: `GET /api/members/search`
- **Query Param**: `name` *(String)*
- **Response**: Matching members.

---

## 🛠️ Usage Notes
- All responses are in **JSON**.
- Replace `<your-server>` with your host, e.g. `http://localhost:8080`.
- Ensure you run the Spring Boot app before hitting endpoints.
