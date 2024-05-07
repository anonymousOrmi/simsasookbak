$(function() {
    $('#start-date').datepicker({
        format: "yyyy-mm-dd",    //데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
        startDate: new Date(),    //달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
        autoclose : true,    //사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
        todayHighlight : true ,    //오늘 날짜에 하이라이팅 기능 기본값 :false
        toggleActive : true,    //이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
        weekStart : 0, //달력 시작 요일 선택하는 것 기본값은 0인 일요일
    }).on('changeDate', function (selected) {
        let startDate = new Date(selected.date);
        startDate.setDate(startDate.getDate());
        $('#end-date').datepicker('setStartDate', startDate);
    }).on('clearDate', function (selected) {
        $('#end-date').datepicker('setStartDate', null);
    });

    $('#end-date').datepicker({
        format: "yyyy-mm-dd",    //데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
        startDate: new Date(),    //달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
        autoclose : true,    //사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
        todayHighlight : true,    //오늘 날짜에 하이라이팅 기능 기본값 :false
        toggleActive : true,    //이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
        weekStart : 0, //달력 시작 요일 선택하는 것 기본값은 0인 일요일
    }).on('changeDate', function (selected) {
        let endDate = new Date(selected.date);
        endDate.setDate(endDate.getDate());
        $('#start-date').datepicker('setEndDate', endDate);
    }).on('clearDate', function (selected) {
        $('#start-date').datepicker('setEndDate', null);
    });

    $('#start-date').datepicker('setDate', 'today');
    $('#end-date').datepicker('setDate', 'today');
});