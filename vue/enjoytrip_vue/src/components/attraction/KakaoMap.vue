<template>
  <div>
    <div id="map"></div>
    <p>
      <button type="button" @click="setOverlayMapTypeId('traffic')">교통정보 보기</button>
      <button type="button" @click="setOverlayMapTypeId('roadview')">로드뷰 도로정보 보기</button>
      <button type="button" @click="setOverlayMapTypeId('terrain')">지형정보 보기</button>
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
      currentTypeId: "",
    };
  },
  props: {
    attractions: [],
  },
  watch: {
    attractions() {
      this.positions = [];
      this.attractions.forEach((attraction) => {
        //console.log(attraction.contentTypeId)
        let obj = {};
        obj.title = attraction.title;
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
        obj.mar = attraction.contentTypeId;
        obj.img = attraction.image;
        this.positions.push(obj);
      });
      this.loadMaker();
    },
  },
  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  created() {
    // if (window.kakao && window.kakao.maps) {
    //   this.loadMap();
    // } else {
    //   const script = document.createElement("script");
    //   /* global kakao */
    //   script.onload = () => kakao.maps.load(this.loadMap);
    //   script.src =
    //     "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
    //   document.head.appendChild(script);
    // }
  },
  methods: {
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      // script.src =
      //   "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
      //   process.env.VUE_APP_KAKAO_MAP_API_KEY +
      //   "&autoload=false";
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";

      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 loadMap 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },

    // 지정한 위치에 마커 불러오기
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      this.deleteMarker();

      // 마커를 생성합니다
      this.markers = [];
      this.infowindow = [];
      this.positions.forEach((position) => {
        console.log(position);
        const infowindow = new kakao.maps.InfoWindow({
          content: `           
             <div class="wrap">
                <div class="info">
                    <div class="title">
                        ${position.title}
                    </div>
                </div>
            </div>`,
        });

        // 마커 이미지를 생성합니다
        //console.log(position)
        const imgSrc = require("@/assets/images/marker_" + position.mar + ".png");
        // 마커 이미지의 이미지 크기 입니다
        const imgSize = new kakao.maps.Size(35, 35);
        const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커의 이미지
        });

        kakao.maps.event.addListener(marker, "mouseover", () => {
          infowindow.open(this.map, marker);
        });
        kakao.maps.event.addListener(marker, "mouseout", () => {
          infowindow.close(this.map, marker);
        });
        this.markers.push(marker);
      });
      // console.log("마커수 ::: " + this.markers.length);
      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );
      this.map.setBounds(bounds);
    },

    deleteMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
        });
      }
    },
    setOverlayMapTypeId(maptype) {
      console.log(maptype);
      var changeMaptype;

      // maptype에 따라 지도에 추가할 지도타입을 결정합니다
      if (maptype === "traffic") {
        // 교통정보 지도타입
        changeMaptype = kakao.maps.MapTypeId.TRAFFIC;
      } else if (maptype === "roadview") {
        // 로드뷰 도로정보 지도타입
        changeMaptype = kakao.maps.MapTypeId.ROADVIEW;
      } else if (maptype === "terrain") {
        // 지형정보 지도타입
        changeMaptype = kakao.maps.MapTypeId.TERRAIN;
      }

      // 이미 등록된 지도 타입이 있으면 제거합니다
      if (this.currentTypeId) {
        this.map.removeOverlayMapTypeId(this.currentTypeId);
      }

      // maptype에 해당하는 지도타입을 지도에 추가합니다
      this.map.addOverlayMapTypeId(changeMaptype);

      // 지도에 추가된 타입정보를 갱신합니다
      this.currentTypeId = changeMaptype;
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 700px;
}
</style>
