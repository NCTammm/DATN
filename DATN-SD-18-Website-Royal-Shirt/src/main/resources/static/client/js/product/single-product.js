
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
