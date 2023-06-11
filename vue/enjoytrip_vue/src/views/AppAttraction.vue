<template>
  <div>
    <div></div>
    <h3 class="underline-steelblue"><br />관광지를 조회하세요<br /></h3>
    <div>
      <b-row class="mt-3">
        <b-col class="sm-3">
          <b-form-select v-model="areaCode" :options="sidos" @change="gugunList"></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select v-model="gugunCode" :options="guguns" @change="search"></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            v-model="contentTypeId"
            id="search-content-id"
            class="form-select me-2"
            name="contentTypeId"
          >
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </b-form-select>
        </b-col>
        <b-col>
          <input
            id="search-keyword"
            name="keyword"
            class="form-control me-2"
            type="search"
            placeholder="검색어"
            aria-label="검색어"
            v-model="keyword"
          />
        </b-col>
        <b-col>
          <!-- <a id="btn-search" class="btn outline-primary" type="button" @click="search"> 검색 </a> -->
          <b-button variant="outline-dark" @click="search">검색</b-button>
        </b-col>
      </b-row>
    </div>
    <!-- <div>관광지</div> -->
    <!-- <attraction-search-bar-vue></attraction-search-bar-vue> -->
    <b-row class="mt-3">
      <b-col class="sm-3">
        <kakao-map :attractions="attractions"></kakao-map>
      </b-col>
      <b-col class="sm-3">
        <attraction-list-vue :attractions="attractions"></attraction-list-vue>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/api/http";
import AttractionListVue from "../components/attraction/AttractionList.vue";
// import AttractionSearchBarVue from "../components/attraction/AttractionSearchBar.vue";
import KakaoMap from "../components/attraction/KakaoMap.vue";

let serviceKey =
  "y2%2BUi4b8CxQtnL3vC%2BOZriRWskhHYtKj%2FrZc870HXKbp2MJVPZYwC7FENHv6VZCW8OC1DaHDfjE7yOwb4k3Rzw%3D%3D";

export default {
  props: { sidoCode: String },
  components: { KakaoMap, AttractionListVue },
  data() {
    return {
      sidos: [{ value: 0, text: "선택하세요" }],
      areaCode: "0",
      gugunCode: "0",
      guguns: [{ value: 0, text: "선택하세요" }],
      keyword: "",
      contentTypeId: "0",
      attractions: [],
    };
  },
  created() {
    let areaUrl =
      "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
      serviceKey +
      "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
    // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
    let areas;
    fetch(areaUrl, { method: "GET" })
      .then((response) => response.json())
      .then((data) => {
        let areas = [...data.response.body.items.item];
        areas.forEach((area) => {
          this.sidos.push({ value: area.code, text: area.name });
        });
      });
    console.log(areas);
  },
  methods: {
    gugunList() {
      this.guguns = [{ value: 0, text: "선택하세요" }];
      if (this.areaCode == "0") {
        // makeAreaOption("0");
        return;
      }
      let areas;
      let areaUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}&numOfRows=25&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${this.areaCode}&_type=json`;
      fetch(areaUrl)
        .then((response) => response.json())
        .then((data) => {
          areas = [...data.response.body.items.item];
          // console.log(areas)
          areas.forEach((area) => {
            this.guguns.push({ value: area.code, text: area.name });
          });
        });
    },
    search() {
      this.attractions = [];
      http
        .post(`/attraction`, {
          sido: this.areaCode,
          gugun: this.gugunCode,
          contentTypeId: this.contentTypeId,
          keyword: this.keyword,
        })
        // http.get(`attraction`)
        .then(({ data }) => {
          // console.log(data);
          this.attractions = [...data];
        });
      // console.log(this.attractions)
    },
  },
};
</script>

<style scoped></style>
