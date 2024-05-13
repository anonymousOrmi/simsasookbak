$('#deleteButton').on('click', function() {

    var memberId = $(this).val();
    console.log('Member ID:', memberId);


    // 서버로 회원 ID를 전송하여 삭제 요청을 합니다.
    $.ajax({
        type: 'POST',
        url: '/admin/delete?memberId=' + memberId,
        success: function(response) {
            // 삭제 요청이 성공적으로 처리되었음을 알립니다.
            console.log('Deleted successfully');
        },
        error: function(xhr, status, error) {
            // 삭제 요청이 실패한 경우에 대한 처리를 수행합니다.
            console.error('Error:', error);
        }
    });
});
