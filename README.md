# midterm-javatech

Web App này được tạo ra bao gồm các tính năng cơ bản của 1 trang ecommerce:
  - Xem sản phẩm
  - Tìm kiếm và filter sản phẩm theo ý muốn
  - Thêm sản phẩm vào giỏ hàng
  - Đăng ký và đăng nhập
  - Trang admin để thêm, xóa, sửa thông tin sảng phẩm

Thư mục Configs sẽ chứa các cài đặt ban đầu bao gồm thêm các đối tượng Account và Product vào database
Thư mục Objects sẽ lưu mã nguồn định nghĩa các enity trong database
Thư mục repositories và services sẽ chứa các repository và service của các enity
File HomeController sẽ điều khiển tất cả các page và sử dụng các service được định nghĩa

Các bước để có thể chạy trên máy local:
  1. Tải toàn bộ src code
  2. Tạo database bằng mySQL với tên là springcommrce
  3. Chạy file Application trong đường dẫn src/main/java/com/example/demo/



Đã cung cấp sẵn các tài khoản để demo:
  1.   user: trico@gmail.com
       pass: 123456
  
  2.   user: khoibu@gmail.com
       pass: 123456
       
  3.   user: admin
       pass: admin

* Tài khoản số 3 là tài khoản admin dùng để thao tác thêm, sửa, xóa các sản phẩm
