const moreButton = document.querySelector('#moreButton');
const reviewListData = document.querySelector("#reviewList").value;

moreButton.addEventListener('click', loadNextReview);

function loadNextReview() {
    console.log(reviewListData);
};