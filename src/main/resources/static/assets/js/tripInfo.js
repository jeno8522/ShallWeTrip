let serviceKey = "y2%2BUi4b8CxQtnL3vC%2BOZriRWskhHYtKj%2FrZc870HXKbp2MJVPZYwC7FENHv6VZCW8OC1DaHDfjE7yOwb4k3Rzw%3D%3D";

//시도 별로 구군 추출
document.getElementById("search-area").addEventListener('change', function () {
    let areaCode = document.getElementById("search-area").value;
    if (areaCode == "0") {
        makeAreaOption("0");
        return;
    }
    let areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=25&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${areaCode}&_type=json`;
    fetch(areaUrl)
        .then((response) => response.json())
        .then((data) => makeAreaOption(data));
    
});

function makeAreaOption(data) { //선택한 도시에 따라 구군에 대한 옵션을 받아온다
    let sel = document.getElementById("search-city");
    sel.innerHTML = `<option value="0" selected >구군</option>` //초기화
    if (data == "0") {
        return;
    }
    let areas = data.response.body.items.item;
    areas.forEach(function (area) {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));
        sel.appendChild(opt);
    });

}

// index page 로딩 후 전국의 시도 설정.
let areaUrl =
"https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
serviceKey +
"&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

// fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
fetch(areaUrl, { method: "GET" })
.then((response) => response.json())
.then((data) => makeOption(data));

function makeOption(data) {
    let areas = data.response.body.items.item;
    // console.log(areas);
    let sel = document.getElementById("search-area");
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));

        sel.appendChild(opt);
    });
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
mapOption = {
  center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
  level: 5, // 지도의 확대 레벨
};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

var bounds = new kakao.maps.LatLngBounds();
var positions; // marker 배열.
var markers = [];

function addMarker(latitude, longitude, contentTypeId, title, addr1, addr2, image, tel) {
	bounds.extend(new kakao.maps.LatLng(latitude, longitude));
    var imageSize = new kakao.maps.Size(27, 27);
    var imageSrc = `/trip/assets/images/marker_${contentTypeId}.png`;

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: new kakao.maps.LatLng(latitude, longitude), // 마커를 표시할 위치
        title: title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
    });
    var infowindow = new kakao.maps.CustomOverlay({
        content: getInfoWindow(title, addr1, addr2, image, tel),
        map: map,
        position: marker.getPosition()       
    });

    infowindow.setMap(null); //기본 닫힘

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
    
}

function resetMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
}

function getInfoWindow(title, addr1, addr2, image, tel) {
    content = `
            <div class="wrap">
                <div class="info">
                    <div class="title">
                        ${title}
                    </div>
                    <div class="body">
                        <div class="img">
                            <img src="${image}" alt="" width="100px">
                        </div>
                        <div class="desc">
                            
                            <div class="ellipsis">${addr1}</div>
                            <div class="jibun ellipsis">${addr2}</div>
                            
                            <div class="phonenumber">${tel}</div>
                        </div>
                    </div>
                </div>
            </div>
    `
    return content;
}
//<div><a href="${info.homepage}" target="_blank" class="link">홈페이지</a></div>
//<div class="overview">${info.overview}</div>
function setBounds() {
    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
        map.setBounds(bounds);
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.setMap(map);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.setMap(null);
    };
}

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

function moveCenter(lat, lng) {
     map.setCenter(new kakao.maps.LatLng(lat, lng));
}
