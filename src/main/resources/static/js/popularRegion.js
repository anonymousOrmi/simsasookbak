async function fetchPopularRegionsByDate()  {
    try {
        const response = await fetch(`/reservation/popular-region?&day=7&limit=6`);
        if(!response.ok) {
            throw new Error(response);
        }
        const popularRegions = await response.json();
        // TODO 인기 지역 Top6 출력 데이터 가공
        /*popularRegions.forEach(region => {

        });*/

    } catch(error) {
        console.error('[Error] fetchPopularRegionsByDate : ', error);
    }
}

fetchPopularRegionsByDate();