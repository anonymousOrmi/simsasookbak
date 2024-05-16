const popularArea = document.querySelector('#popular-area');

async function fetchPopularRegionsByDate()  {
    try {
        const response = await fetch(`/reservation/popular-region?&day=7&limit=6`);
        if(!response.ok) {
            throw new Error(response);
        }
        const popularRegions = await response.json();
        let element = "";
        popularRegions.forEach(data => {
            const imageUrl = '/img/explore-tour-1.jpg';
            const siteUrl = '/accommodation?keyword=' + encodeURIComponent(data?.region);
            element += `<div class="col-md-6 col-lg-4">
                            <div class="national-item">
                                <img alt="Image" class="img-fluid w-100 rounded" src="${imageUrl}"> 
                                <div class="national-content">
                                    <div class="national-info">
                                        <h5 class="text-white text-uppercase mb-2">${data?.region}</h5>
                                        <a class="btn-hover text-white" href="${siteUrl}">숙소 모두 보기 <i class="fa fa-arrow-right ms-2"></i></a>
                                    </div>
                                </div>
                                <div class="national-plus-icon">
                                    <a class="my-auto" href="${siteUrl}"><i class="fas fa-link fa-2x text-white"></i></a>
                                </div>
                            </div>
                        </div>`;
        });
        popularArea.insertAdjacentHTML('beforeend', element);
    } catch(error) {
        console.error('[Error] fetchPopularRegionsByDate : ', error);
    }
}

fetchPopularRegionsByDate();