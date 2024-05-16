const moreButton = document.querySelector('#moreButton');
const reviewListData = document.querySelector("#reviewList").value;

moreButton.addEventListener('click', loadNextReview);

function loadNextReview() {
    console.log(reviewListData);
};

function deleteReview(reviewId) {
    fetch(`/review/${reviewId}`, {method: 'Delete'})
        .then(response => {
            if (!response.ok) {
                console.error('Error deleting review:', response.statusText);
                return;
            }
            alert('댓글이 삭제되었습니다.');
            location.reload();
        })
        .catch(error => {
            console.error('Error deleting review:', error);
        })
}