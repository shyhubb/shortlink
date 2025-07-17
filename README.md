# Link3s Shortlink Service Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.3-green)
![Maven](https://img.shields.io/badge/Maven-3.x-red)

![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![JWT](https://img.shields.io/badge/JWT-Authentication-yellow)
![Deployed On Render](https://img.shields.io/badge/Deployed%20on-Render-darkgreen)

Dự án Link3s Shortlink Service là một dịch vụ rút gọn URL mạnh mẽ và linh hoạt, được xây dựng bằng Spring Boot. Nó cung cấp các API để tạo, quản lý và chuyển hướng các liên kết rút gọn, hỗ trợ cả người dùng công khai và người dùng đã đăng ký.

---

## 🚀 Tính năng nổi bật

* **Rút gọn URL ngẫu nhiên:** Tạo các mã ngắn ngẫu nhiên cho URL dài.
* **Rút gọn URL tùy chỉnh:** Cho phép người dùng chọn mã ngắn riêng của họ (yêu cầu đăng nhập).
* **Giới hạn tần suất:** Giới hạn số lượng link tạo theo địa chỉ IP để ngăn chặn lạm dụng.
* **Xác thực mã ngắn:** Đảm bảo mã tùy chỉnh hợp lệ (ký tự, độ dài) và không xung đột với các đường dẫn nội bộ của hệ thống.
* **Quản lý link cá nhân:** Người dùng đã đăng ký có thể xem và quản lý các link họ đã tạo.
* **Xác thực người dùng:** Hệ thống đăng ký/đăng nhập an toàn với JWT (JSON Web Tokens).
* **Chuyển hướng hiệu quả:** Chuyển hướng nhanh chóng từ short link đến original link.

---

## 🛠️ Công nghệ sử dụng

### Backend
* **Ngôn ngữ:** Java 17
* **Framework:** Spring Boot 3.5.3
* **Quản lý Dependency:** Apache Maven
* **Cơ sở dữ liệu:** PostgreSQL (có thể cấu hình với MySQL)
* **ORM:** Spring Data JPA
* **Bảo mật:** Spring Security, JWT (jjwt-api, jjwt-impl, jjwt-jackson)
* **Utility:** Project Lombok, Apache Commons Validator

### Frontend (Giao diện người dùng)
_**Lưu ý:** Phần frontend được gợi ý để xây dựng bằng HTML, CSS và JavaScript thuần, sử dụng Bootstrap 5 cho giao diện._

---

## 📦 Cấu trúc Dự án

Dự án được tổ chức theo cấu trúc chuẩn của Spring Boot, với các package chính:

* `ltd.tinyurl.shortlink.controller`: Các REST API endpoint.
* `ltd.tinyurl.shortlink.service`: Chứa logic nghiệp vụ.
* `ltd.tinyurl.shortlink.repository`: Các giao diện truy cập dữ liệu (Spring Data JPA).
* `ltd.tinyurl.shortlink.entity`: Các lớp Entity ánh xạ tới bảng trong cơ sở dữ liệu.
* `ltd.tinyurl.shortlink.dto`: Các Data Transfer Object (DTO) cho Request và Response.
* `ltd.tinyurl.shortlink.webconstants`: Các hằng số được sử dụng trong toàn bộ ứng dụng (đường dẫn API, thông báo lỗi, giới hạn...).

---

## 🚀 Bắt đầu (Local Development)

### Yêu cầu
* Java 17 JDK
* Apache Maven
* Cơ sở dữ liệu PostgreSQL hoặc MySQL (đảm bảo dịch vụ DB đang chạy)

### Cài đặt
1.  **Clone repository:**
    ```bash
    git clone [URL_CỦA_REPOSITORY_CỦA_BẠN]
    cd shortlink
    ```
2.  **Cấu hình cơ sở dữ liệu:**
    * Mở file `src/main/resources/application.properties` (hoặc `application.yml`).
    * Cập nhật thông tin kết nối cơ sở dữ liệu của bạn:
        ```properties
        # PostgreSQL example
        spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update # hoặc create để tạo schema tự động
        spring.jpa.show-sql=true
        ```
        Hoặc nếu dùng MySQL:
        ```properties
        # MySQL example
        spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&serverTimezone=UTC
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        ```
3.  **Build dự án:**
    ```bash
    mvn clean install
    ```
4.  **Chạy ứng dụng:**
    ```bash
    mvn spring-boot:run
    ```
    Hoặc chạy file JAR sau khi build:
    ```bash
    java -jar target/shortlink-0.0.1-SNAPSHOT.jar
    ```
Ứng dụng sẽ chạy trên cổng mặc định 8080 (hoặc cổng đã cấu hình).

---

## 🌐 API Endpoint Đã Triển Khai

Backend của dịch vụ đã được triển khai và có thể truy cập công khai tại:

**`https://link3s.onrender.com/`**

Bạn có thể sử dụng các endpoint này để tương tác với dịch vụ. Ví dụ:

* **Đăng ký:** `POST https://link3s.onrender.com/v1/auth/register`
* **Đăng nhập:** `POST https://link3s.onrender.com/v1/auth/login`
* **Tạo link ngẫu nhiên (công khai):** `POST https://link3s.onrender.com/v1/public/shortlink/create`
* **Tạo link tùy chỉnh (yêu cầu JWT):** `POST https://link3s.onrender.com/v1/user/link/create/custom`
* **Chuyển hướng link:** `GET https://link3s.onrender.com/{shortCode}`

---

## 🤝 Đóng góp

Đóng góp luôn được hoan nghênh! Vui lòng fork repository, tạo một nhánh mới cho tính năng của bạn và gửi Pull 
