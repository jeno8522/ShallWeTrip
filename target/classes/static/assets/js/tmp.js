document.getElementById("selected-area").addEventListener('change', function () {
    let areaCode = document.getElementById("selected-area").value;
    if (areaCode == "") {
        makeAreaOption("");
        return;
    }
    let areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=25&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${areaCode}&_type=json`;
    fetch(areaUrl)
        .then((response) => response.json())
        .then((data) => makeAreaOption(data)); 
    
});

function makeAreaOption(data) {
    let sel = document.getElementById("selected-city");
    sel.innerHTML = `<option  disabled="disabled" value="">구군선택</option>` //초기화
    if (data == "") {
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

let sigunguCode = '';
document.getElementById("selected-city").addEventListener('change', function () {
    // let areaCode = document.getElementById("selected-area").value;
    sigunguCode = document.getElementById("selected-city").value;
    // for (var type = 1; type <= 8; type++) {
    //     if (document.getElementById(`check${type}`).checked === false) {
    //         continue;
    //     }
    //     console.log(document.getElementById(`check${type}`).checked);
    //     let contentTypeId = document.getElementById(`check${type}`).value;
    //     console.log("areacode : " + areaCode + " sigun : " + sigunguCode + " contents : " + contentTypeId);
    //     let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=${contentTypeId}&areaCode=${areaCode}&sigunguCode=${sigunguCode}`;
    //     fetch(searchUrl)
    //         .then((response) => response.json())
    //         .then((data) => addTrips(data));
    // }
    
    //
 
});

var positions = [];
var bounds

function setBounds() {
        let areaCode = document.getElementById("selected-area").value;

        for (var type = 1; type <= 8; type++) {
            if (document.getElementById(`check${type}`).checked === false) {
                continue;
            }
            console.log(document.getElementById(`check${type}`).checked);
            let contentTypeId = document.getElementById(`check${type}`).value;
            console.log("areacode : " + areaCode + " sigun : " + sigunguCode + " contents : " + contentTypeId);
            let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=${contentTypeId}&areaCode=${areaCode}&sigunguCode=${sigunguCode}`;
            fetch(searchUrl)
                .then((response) => response.json())
                .then((data) => addTrips(data));
        }

    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
    map.setBounds(bounds);
}

function positionMarker(content,latlng,img) {
    this.content = content;
    this.latlng = latlng;
    this.img = img;
}

function addTrips(data) {

    let trips = data.response.body.items.item;
    
    if (!trips) {
        return;
    }
    trips.forEach(function (trip) {
        // titles.push(trip.title)
        // img.push(trip.firstimage)
        positions.push(new positionMarker(trip.title,new kakao.maps.LatLng(trip.mapy, trip.mapx),trip.firstimage) ); //points.push(new kakao.maps.LatLng(trip.mapy.value, trip.mapx.value));
        console.log(positions)
        
    });
    
    
    for (var i = 0; i < positions.length; i++) {
    // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
        var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng // 마커의 위치
    });
    // 마커에 표시할 인포윈도우를 생성합니다 
    var infowindow = new kakao.maps.InfoWindow({
        // content: positions[i].content // 인포윈도우에 표시할 내용
        content: '<div class="overlaybox">' +
            '    <div class="boxtitle">' + positions[i].content + '</div>' +
            '<img src="'+positions[i].img+'" width="60px">'+
            '</div>'
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
        
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(positions[i].latlng);
    }
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}


// 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
bounds = new kakao.maps.LatLngBounds();    


var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);