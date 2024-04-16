
document.addEventListener("DOMContentLoaded", function() {
    var quantityInput = document.getElementById('quantity');

    // Bắt sự kiện khi giá trị của input thay đổi
    quantityInput.addEventListener('change', function() {
        var quantityValue = parseInt(quantityInput.value);

        // Kiểm tra xem số lượng có lớn hơn 0 không
        if (quantityValue < 1) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Số lượng phải lớn hơn 1.'
            });
            this.value = 1;
            return;
        }
        // Kiểm tra xem giá trị nhập vào có phải là số không
        if (!(/^\d*$/.test(quantityValue))) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi!',
                text: 'Vui lòng chỉ nhập các ký tự số.'
            });
            this.value = 1;
            return;
        }
    });
});

// Hàm add to cart
function addToCart(button) {
    var id = button.getAttribute("data-id");

    var colorId = document.getElementById('colorSelect').value;
    var sizeId = document.getElementById('sizeSelect').value;
    var quantity = document.getElementById('quantity').value;

    var maxQuantity = parseInt(document.getElementById('quantity').getAttribute('max'));

    if (!colorId || !sizeId || !quantity) {
        alert('Vui lòng chọn màu sắc, kích thước và nhập số lượng.');
        return;
    }
    // Kiểm tra số lượng tồn kho
    if (isNaN(quantity) || quantity > maxQuantity) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Số lượng vượt quá số lượng tối đa cho phép.'
        });
        return;
    }

    var cartDetailDTO = {
        productDetail: {
            color: { id: colorId },
            size: { id: sizeId }
        },
        quantity: quantity
    };

    $.ajax({
        type: 'POST',
        url: '/add-to-cart/' + id,
        contentType: 'application/json',
        data: JSON.stringify(cartDetailDTO),
        success: function (response) {
            Swal.fire({
                icon: 'success',
                title: 'Thành công!',
                text: 'Sản phẩm đã được thêm vào giỏ hàng thành công!'
            });
        },
        error: function (xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Thất bại!',
                text: 'Không thể thêm sản phẩm vào giỏ hàng.'
            });
        }
    });
}
