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

function approveReservation(reservationId) {
    fetch(`/api/reservations/approve/${reservationId}`, {method:'PUT'})
        .then(response => {
            if (!response.ok) {
                console.error('Error approving reservation:', response.statusText);
                return;
            }
            alert('예약이 확정되었습니다.');
            location.reload(true);
        })
        .catch(error=> {
            console.error('Error approving reservation:', error);
        });
}

// function updateReservation(reservationId) {
//     fetch(`/api/reservations/renew/${reservationId}`, {method:'PUT'})
//         .then(response => {
//             if (!response.ok) {
//                 console.error('Error updating reservation:', response.statusText);
//                 return;
//             }
//             alert('예약이 갱신되었습니다.');
//             location.reload(true);
//         })
//         .catch(error=> {
//             console.error('Error updating reservation:',error);
//         });
// }

function getUpdatePage(reservationId) {
    fetch(`/reservation/renew/${reservationId}`, {method:'GET'});
}