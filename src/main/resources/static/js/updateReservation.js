function submitForm(reservationId) {
    var formData = {
        startDate: document.getElementById("date-picker1").value,
        endDate: document.getElementById("date-picker2").value,
        requestMessage: document.getElementById("request").value
    };

    fetch(`/api/reservations/renew/${reservationId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                console.error('Error updating reservation:', response.statusText);
                return;
            }
            alert('예약이 수정되었습니다.');
            location.replace("/reservation");
        })
        .catch(error => {
            console.error('Error updating reservation:', error);
        })
}