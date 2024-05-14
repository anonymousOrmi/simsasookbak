$(document).on('click', '.deleteButton', function() {
    var memberId = parseInt($(this).val(), 10);
    console.log('Member ID:', memberId);

    // 탈퇴 확인 메시지 표시
    var confirmMessage = "탈퇴하시겠습니까?";
    if (confirm(confirmMessage)) {
        $.ajax({
            type: 'POST',
            url: '/admin/delete?memberId=' + memberId,
            success: function(response) {
                // 삭제 요청이 성공적으로 처리된 후에만 페이지를 변경합니다.
                window.location.reload();
                console.log(location.reload );
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }
});

$(document).ready(function() {
    $('.page-link').click(function(event) {
        event.preventDefault();
        var pageNum = parseInt($(this).text());
        var url = '/admin/getAllMember?page=' + pageNum;
        window.location.href = url;
    });
});


