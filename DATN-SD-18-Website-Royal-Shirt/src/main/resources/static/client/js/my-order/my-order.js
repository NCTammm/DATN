
$(document).ready(function(){
    $('#closeModal').click(function(){
        $('#showModal').modal('hide');
    });
});

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
}

function getOrderDetails(button) {
    var orderId = button.getAttribute("data-id");
    $.ajax({
        type: "GET",
        url: "/rest/my-order/order-detail/" + orderId,
        success: function(data) {
            console.log(data);
            $('#orderDetailBody').empty();
            $.each(data, function(index, od) {
                var row = '<tr>' +
                    '<td>' + (index + 1) + '</td>';
                if (od.productImage && od.productName) {
                    row += '<td><img src="' + od.productImage + '" alt="Product Image" class="img-thumbnail" style="width: 50px; height: 50px;" onclick="redirectToProductPage(' + od.productId + ')"></td>' + // Thêm hàm onclick
                        '<td>' + od.productName + '</td>' +
                        '<td>' + od.colorName + '</td>' +
                        '<td>' + od.sizeName + '</td>';
                } else {
                    row += '<td></td>' +
                        '<td></td>';
                }
                row += '<td>' + formatCurrency(od.price) + '</td>' +
                    '<td>' + od.quantity + '</td>' +
                    '<td>' + formatCurrency(od.price * od.quantity) + '</td>' +
                    '</tr>';
                $('#orderDetailBody').append(row);
            });
            $('#showModal').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("AJAX Error:", textStatus, errorThrown);
            alert('Có lỗi xảy ra khi lấy chi tiết đơn hàng.');
        }
    });
}

// Hàm để chuyển hướng đến trang sản phẩm
function redirectToProductPage(productId) {
    window.location.href = '/single-product/' + productId;
}

