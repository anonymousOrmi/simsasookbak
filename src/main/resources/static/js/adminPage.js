$(document).on('click', '.deleteButton', function() {
    var memberId = parseInt($(this).val(), 10);
    console.log('Member ID:', memberId);

    // 탈퇴 확인 메시지 표시
    var confirmMessage = "탈퇴하시겠습니까?";
    if (confirm(confirmMessage)) {

        // 서버로 회원 ID를 전송하여 삭제 요청을 합니다.
        $.ajax({
            type: 'POST',
            url: '/admin/delete?memberId=' + memberId,
            success: function(response) {
                // 삭제 요청이 성공적으로 처리되었음을 알립니다.
                window.location.reload();
                console.log('Deleted successfully');
            },
            error: function(xhr, status, error) {
                // 삭제 요청이 실패한 경우에 대한 처리를 수행합니다.
                console.error('Error:', error);
            }
        });
    }
});
