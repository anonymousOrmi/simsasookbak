/* 유투브 데이터 조회 */
$.ajax({
    url: '/api/youtube',
    method: 'GET',
    dataType : 'json',
    success: function (response) {
        let html = '';
        $.each(response, function (index, data) {
            console.log('data', data);
            let videoId = $(data).prop('videoId');
            html += '<div class="col-sm-6 col-md-6 col-lg-4 col-xl-3">' +
                '<div class="ratio ratio-16x9">' +
                `<iframe src="https://www.youtube.com/embed/${videoId}" 
                         title="YouTube video" allowfullscreen 
                         th:if="${videoId != null}"></iframe>` +
                '</div></div>';
        });
        $('#youtube').html(html);
    },
    error: function (error) {
        console.error('Youtube Error : ', error);
        $('#youtube-container').html('');
        alert(error.responseText);
    }
});
