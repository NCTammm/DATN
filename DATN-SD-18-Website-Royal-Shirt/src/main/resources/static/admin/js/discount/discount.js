//Show form
$(document).ready(function () {
    $('#showFormDiscount').click(function () {
        $('.modal-title').text("Thêm Giảm Giá");
        $('#DiscountModal').modal('show');
    });
    $('#closeFormDiscount').click(function () {
        $('#DiscountModal').modal('hide');
    });
});

//Add and update discount
function saveDiscount() {
    // Lấy dữ liệu từ biểu mẫu
    var discountName = $("#discountName").val().trim();
    var currentTime = moment().format('YYYY-MM-DD');
    var status = 0;
    var discountId = $("#discountForm").attr("discount-id-update");

    // Check thông tin
    if (!checkInputDiscount(discountName)) {
        return;
    }

    var dataToSend = {
        name: discountName,
        status: status,
    }

    // Nếu discountId tồn tại -> update, ngược lại -> add
    if (discountId) {
        dataToSend.id = discountId;
        dataToSend.updatedDate = currentTime;
    } else {
        // Kiểm tra trùng tên Giảm Giá
        if (!checkDuplicateDiscount(discountName)) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Tên giảm giá đã tồn tại!'
            });
            return false;
        }
        dataToSend.createdDate = currentTime;
    }

    var url = discountId ? "/admin/rest/discount/update/" + discountId : "/admin/rest/discount/add";

    var method = discountId ? "PUT" : "POST";

    // Gửi yêu cầu AJAX
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json",
        data: JSON.stringify(dataToSend),
        success: function (response) {
            console.log("Lưu giảm giá thành công!");

            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Lưu giảm giá thành công!',
                didClose: function () {
                    location.reload();
                }
            });
        },
        error: function (error) {
            console.error("Lỗi khi lưu giảm giá:", error);

            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi lưu giảm giá!'
            });
        }
    });
}

// Check thông tin
function checkInputDiscount(discountName) {
    // Kiểm tra xem các trường có rỗng không
    if (discountName === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin giảm giá!'
        });
        return false;
    }

    //Check Tên giảm giá
    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(discountName)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên giảm giá chỉ được chứa chữ cái và khoảng trắng!'
        });
        return false;
    }

    return true;
}


// Check trùng Tên giảm giá
function checkDuplicateDiscount(discountName) {
    var isDuplicateName;
    // Gửi yêu cầu AJAX để kiểm tra trùng tên giảm giá
    $.ajax({
        type: "POST",
        url: "/admin/rest/discount/checkDuplicateName",
        contentType: "application/json",
        data: JSON.stringify({name: discountName}),
        async: false,
        success: function (response) {
            isDuplicateName = response.isDuplicateName;
        },
        error: function (error) {
            console.error("Lỗi khi kiểm tra trùng tên giảm giá:", error);
        }
    });
    return isDuplicateName;
}


// Hàm để cập nhật biểu mẫu với dữ liệu giảm giá
function updateDiscountForm(element) {
    //Chỉnh sửa tên modal
    $('.modal-title').text("Chỉnh sửa Giảm giá");

    var discountId = element.getAttribute("data-discount-id");
    // Thêm thuộc tính để kiểm tra xem add hay update
    $('#discountForm').attr('discount-id-update', discountId);
    // Thực hiện AJAX request để lấy dữ liệu giảm giá từ backend
    $.ajax({
        type: 'GET',
        url: '/admin/rest/discount/formUpdate/' + discountId,
        success: function (discount) {
            // Hiển thị hộp thoại modal
            $('#DiscountModal').modal('show');

            // Điền dữ liệu vào các trường biểu mẫu
            $('#discountName').val(discount.name);

            // Lắng nghe sự kiện đóng modal
            $('#DiscountModal').on('hidden.bs.modal', function () {
                // Xóa thuộc tính discount-id-update khi modal đóng
                $('#discountForm').removeAttr('discount-id-update');
                // Làm mới input
                $('#discountName').val(null);
            });
        },
        error: function (error) {
            console.log('Error fetching discount data:', error);
            // Xử lý lỗi nếu cần
        }
    });
}

//Set Status
function toggleStatus(checkbox) {
    var discountId = checkbox.getAttribute("data-discount-id");
    // Gửi yêu cầu AJAX để cập nhật trạng thái của giảm giá
    //Sử dụng jQuery
    $.ajax({
        type: "POST",
        url: "/admin/rest/discount/setStatus/" + discountId,
        success: function (response) {
            // Xử lý thành công, nếu cần
            console.log("Cập nhật trạng thái thành công");

            // Hiển thị thông báo thành công sử dụng SweetAlert2
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Trạng thái đã được cập nhật thành công!'
            });

        },
        error: function (error) {
            // Xử lý lỗi, nếu cần
            console.error("Lỗi khi cập nhật trạng thái");

            // Hiển thị thông báo thất bại sử dụng SweetAlert2
            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi cập nhật trạng thái.'
            });
        }
    });
}