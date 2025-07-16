# Giai đoạn Build: Sử dụng image Maven với OpenJDK 17
# Image này đã bao gồm Maven, rất tiện lợi cho việc build dự án.
FROM maven:3-openjdk-17 AS build

# Thiết lập thư mục làm việc bên trong container
WORKDIR /app

# Sao chép toàn bộ mã nguồn và cấu hình dự án vào thư mục làm việc
# Lệnh này đơn giản và hiệu quả cho hầu hết các dự án Spring Boot.
COPY . .

# Biên dịch ứng dụng Spring Boot và đóng gói thành file JAR
# -DskipTests bỏ qua các bài kiểm tra để tăng tốc quá trình build.
RUN mvn clean package -DskipTests

# Giai đoạn Runtime: Sử dụng một image JRE nhẹ hơn để chạy ứng dụng
# eclipse-temurin:17-jre-jammy là một lựa chọn tốt và đáng tin cậy cho JRE 17 slim
# Điều này giúp giảm đáng kể kích thước image cuối cùng.
FROM eclipse-temurin:17-jre-jammy

# Thiết lập thư mục làm việc bên trong container cho ứng dụng
WORKDIR /app

# Sao chép file JAR đã được build từ giai đoạn 'build'
# Tên file JAR của bạn là shortlink-0.0.1-SNAPSHOT.jar.
# Đảm bảo tên này khớp chính xác với tên file được tạo ra trong thư mục target/ của dự án.
COPY --from=build /app/target/shortlink-0.0.1-SNAPSHOT.jar shortlink.jar

# Mở cổng mà ứng dụng Spring Boot của bạn đang lắng nghe (mặc định là 8080)
EXPOSE 8080

# Lệnh để chạy ứng dụng Spring Boot khi container khởi động
# Chạy file JAR trực tiếp với java -jar là cách phổ biến cho các ứng dụng Spring Boot đóng gói dạng executable JAR.
ENTRYPOINT ["java", "-jar", "shortlink.jar"]
