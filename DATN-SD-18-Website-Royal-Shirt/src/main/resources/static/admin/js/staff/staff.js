
//Show form
$(document).ready(function () {
    $('#showModalStaff').click(function () {
        $('.modal-title').text("Thêm Nhân Viên");
        $('#modalStaff').modal('show');
    });
    $('#closeFormStaff').click(function () {
        $('#modalStaff').modal('hide');
    });
});


function readURL(input, thumbimage) {
    if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
        var reader = new FileReader();
        reader.onload = function (e) {
            $("#thumbimage").attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
    else { // Sử dụng cho IE
        $("#thumbimage").attr('src', input.value);
    }
    $("#thumbimage").show();
    $('.filename').text($("#uploadfile").val());
    $('.Choicefile').css('background', '#14142B');
    $('.Choicefile').css('cursor', 'default');
    $(".removeimg").show();
    $(".Choicefile").unbind('click');

}
$(document).ready(function () {
    $(".Choicefile").bind('click', function () {
        $("#uploadfile").click();
    });
    $(".removeimg").click(function () {
        $("#thumbimage").attr('src', '').hide();
        $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
        $(".removeimg").hide();
        $(".Choicefile").bind('click', function () {
            $("#uploadfile").click();
        });
        $('.Choicefile').css('background', '#14142B');
        $('.Choicefile').css('cursor', 'pointer');
        $(".filename").text("");
    });
})

// set status staff
function setStatusStaff(button) {
    var staffId = button.getAttribute("data-id");

    Swal.fire({
        title: "Xác nhận",
        text: "Bạn có chắc chắn muốn thay đổi trạng thái của nhân viên này?",
        icon: "warning",
        showCancelButton: true, // Hiển thị nút Hủy
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Đồng ý",
        cancelButtonText: "Hủy"
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "POST",
                url: "/admin/rest/staffs/setStatus/" + staffId,
                success: function (response) {
                    Swal.fire({
                        title: "Thành công!",
                        text: "Thay đổi trạng thái thành công!",
                        icon: "success",
                    }).then(() => {
                        // Reload trang sau khi thành công
                        location.reload();
                    });
                },
                error: function () {
                    Swal.fire({
                        title: "Lỗi!",
                        text: "Đã xảy ra lỗi khi thay đổi trạng thái!",
                        icon: "error",
                    });
                }
            });
        }
    });
}

// save or update staff
function saveOrUpdateStaff() {
    var name = $("#name").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    var role = $("#role").val();
    var password =  $("#password").val();
    var image = $("#uploadfile").prop('files')[0];
    var currentTime = moment().format('YYYY-MM-DD');
    var staffId = $("#staffForm").attr("staff-id-update");

    var dataSend = {
        name: name,
        email: email,
        phone: phone,
        address: address,
        password: password,
        roleId: role,
        avatar: image
    }
    console.log(dataSend);
    // Check thông tin
//    if (!validateInputStaff()) {
//        return;
//    }

    // Nếu  idStaff tồn tại -> update, ngược lại -> create
    if (staffId) {
        dataSend.id = staffId;
        dataSend.updatedDate = currentTime;
    } else {
        // Kiểm tra trùng email
//        if (!validateDuplicateEmail(email)) {
//            Swal.fire({
//                icon: 'error',
//                title: 'Lỗi!',
//                text: 'Email đã tồn tại trong hệ thống!'
//            });
//            return false;
//        }
        dataSend.createdDate = currentTime;
    }

    var url = staffId ? "/admin/rest/staffs/update/" + staffId : "/admin/rest/staffs/create";
    var method = staffId ? "PUT" : "POST";

    // Gửi yêu cầu AJAX
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json",
        data: JSON.stringify(dataSend),
        success: function (response) {
            console.log("Lưu nhân viên thành công!");
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Lưu nhân viên thành công!',
                didClose: function () {
                    location.reload();
                }
            });
        },
        error: function (error) {
            console.error("Lỗi khi lưu nhân viên:", error);
            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Có lỗi xảy ra khi lưu nhân viên!'
            });
        }
    });
}

//validate input form staff
function validateInputStaff() {
    var name = $("#name").val().trim();
    var email = $("#email").val().trim();
    var phone = $("#phone").val().trim();
    var address = $("#address").val().trim();
    var password = $("#password").val().trim();

    // Kiểm tra xem các trường có rỗng không
    if (name === "" || email === "" || phone === "" || address === "") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Vui lòng điền đầy đủ thông tin!'
        });
        return false;
    }
    //Check Tên thương hiệu
    var nameRegex = /^[a-zA-ZÀ-ỹ\s]+$/;
    if (!nameRegex.test(name)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Tên nhân viên không hợp lệ!'
        });
        return false;
    }
    // Kiểm tra định dạng email
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Email không hợp lệ!'
        });
        return false;
    }
    // Kiểm tra định dạng số điện thoại
    var phoneRegex = /^(0|\+84)\d{9,10}$/;
    if (!phoneRegex.test(phone)) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Số điện thoại không hợp lệ!'
        });
        return false;
    }
}

// Check trùng email staff
function validateDuplicateEmail(email) {
    var existEmail;
    // Gửi yêu cầu AJAX để kiểm tra trùng email
    $.ajax({
        type: "POST",
        url: "/admin/rest/staffs/validateDuplicateEmail",
        contentType: "application/json",
        data: JSON.stringify({name: email}),
        async: false,
        success: function (response) {
            existEmail = response.existEmail;
        },
        error: function (error) {
            console.error("Lỗi khi kiểm tra trùng tên Email:", error);
        }
    });
    return existEmail;
}

function updateStaff(element) {
    //Chỉnh sửa tên modal
    $('.modal-title').text("Chỉnh sửa nhân viên");

    var staffId = element.getAttribute("data-id");

    // Thêm thuộc tính để kiểm tra xem add hay update
    $('#staffForm').attr('staff-id-update', staffId);
    $.ajax({
        type: 'GET',
        url: '/admin/rest/staffs/formUpdate/' + staffId,
        success: function (staff) {
            // Hiển thị hộp thoại modal
            $('#modalStaff').modal('show');

            // Điền dữ liệu vào các trường biểu mẫu
            $('#name').val(staff.name);
            $('#email').val(staff.email);
            $('#phone').val(staff.phone);
            $('#address').val(staff.address);
            $('#uploadfile').val(staff.avatar);
            $('#role').val(staff.role);

            // Lắng nghe sự kiện đóng modal
            $('#modalStaff').on('hidden.bs.modal', function () {
                // Xóa thuộc tính brand-id-update khi modal đóng
                $('#staffForm').removeAttr('brand-id-update');

                // Làm mới input
                $('#name').val(null);
                $('#email').val(null);
                $('#phone').val(null);
                $('#address').val(null);
                $('#address').val(null);
                $('#role').val(null);
                $('#uploadfile').val(null);
            });
        },
        error: function (error) {
            console.log('Error fetching brand data:', error);
            // Xử lý lỗi nếu cần
        }
    });
}




