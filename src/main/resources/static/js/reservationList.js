function cancelReservation(reservationId) {
    fetch(`/api/reservations/${reservationId}`, {method:'PUT'})
        .then(response => {
            if (!response.ok) {
                console.error('Error cancelling reservation:', response.statusText);
                return;
            }
            alert('예약이 취소되었습니다.');
            location.reload(true);
        })
        .catch(error=> {
            console.error('Error cancelling reservation:', error);
        });
}