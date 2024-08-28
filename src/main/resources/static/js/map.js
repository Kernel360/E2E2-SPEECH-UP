document.addEventListener("DOMContentLoaded", function () {
    const academyFeature = document.getElementById("academy-feature");

    if (academyFeature) {
        academyFeature.addEventListener("click", function () {
            const url = `/map`;
            const name = 'popup-map'
            const options = 'top=10, left=10, width=800, height=600, status=no, menubar=no, toolbar=no, resizable=no, scrollbars=no, fullscreen=no';
            window.open(url, name, options);
        });
    }
});

const loadingBar = document.getElementById('loading-bar');
loadingBar.style.display = 'block';

// 마커를 담을 배열입니다
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var userLocate = [];
// HTML5의 geolocation으로 사용할 수 있는지 확인합니다
if (navigator.geolocation) {

    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {

        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation 으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

        userLocate.push(lat);
        userLocate.push(lon);
        console.log('lat : ' + userLocate[0]);
        console.log('lng : ' + userLocate[1]);
        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);

        // 키워드로 장소를 검색합니다
        searchPlaces();
    });

} else { // HTML5의 GeoLocation 을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
        message = 'geolocation을 사용할수 없어요..'

    displayMarker(locPosition, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: locPosition
    });

    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);

    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
}

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( '스피치학원', placesSearchCB);
}

// 장소검색이 완료됐을 때 호출되는 콜백 함수입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        let searchRanges = [5000, 10000, 15000]; // 검색 거리 범위 (5km, 10km, 15km)
        let foundPlaces = false; // 검색 결과를 찾았는지 여부

        for (let range of searchRanges) {
            let filteredPlaces = data.filter(function(place) {
                let placePosition = new kakao.maps.LatLng(place.y, place.x);
                let distance = getDistance(userLocate[0], userLocate[1], placePosition.getLat(), placePosition.getLng());
                return distance <= range;
            });

            if (filteredPlaces.length > 0) {
                displayPlaces(filteredPlaces);
                displayPagination(pagination);
                foundPlaces = true; // 검색 결과를 찾음
                break; // 검색 결과를 찾았으므로 반복문 종료
            }
        }

        if (!foundPlaces) {
            alert('회원님 주변 15km 이내에 스피치학원이 없습니다.');
            closeSearchWindow(); // 검색 창 닫기
        }

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
        closeSearchWindow(); // 검색 창 닫기
    } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
        closeSearchWindow(); // 검색 창 닫기
    }

    loadingBar.style.display = 'none'; // 로딩바 숨기기
}

// 검색 창 닫기 함수
function closeSearchWindow() {
    window.close();
}


// Haversine 공식을 적용한 거리계산 코드
function getDistance(lat1, lon1, lat2, lon2) {
    var R = 6371e3; // 지구 반지름 (미터 단위)
    var φ1 = lat1 * Math.PI / 180;
    var φ2 = lat2 * Math.PI / 180;
    var Δφ = (lat2 - lat1) * Math.PI / 180;
    var Δλ = (lon2 - lon1) * Math.PI / 180;

    var a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2) +
        Math.cos(φ1) * Math.cos(φ2) *
        Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    var distance = R * c; // 두 좌표 간의 거리 (미터 단위)
    return distance;
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
    var listEl = document.getElementById('placesList'),
        menuEl = document.getElementById('menu_wrap'),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds();

    removeAllChildNods(listEl); // 이전 결과 목록을 제거
    removeMarker(); // 지도에서 이전 마커를 제거

    for (var i = 0; i < places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i),
            itemEl = getListItem(i, places[i]);

        bounds.extend(placePosition);

        (function (marker, title) {
            itemEl.onmouseover = function () {
                displayInfowindow(marker, title);
            };
            itemEl.onmouseout = function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    map.setBounds(bounds); // 지도 범위 재설정
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
        itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
            '<div class="info">' +
            '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
            '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>';
    }

    itemStr += '  <span class="tel">' + places.phone  + '</span>' +
        '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }
    markers = [];
}

// 검색 결과 목록을 페이지당 10개씩 나누는 함수입니다
function paginateResults(results, pagination) {
    var perPage = 10; // 페이지당 결과 개수
    var page = pagination.current; // 현재 페이지 번호
    var totalPages = Math.ceil(results.length / perPage); // 총 페이지 수

    var start = (page - 1) * perPage;
    var end = start + perPage;

    return {
        results: results.slice(start, end),
        pagination: {
            current: page,
            last: totalPages
        }
    };
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment();

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
    }

    for (var i = 1; i <= pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    ps.keywordSearch('스피치학원', placesSearchCB, { page: i });
                };
            })(i);
        }

        fragment.appendChild(el);
    }

    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}