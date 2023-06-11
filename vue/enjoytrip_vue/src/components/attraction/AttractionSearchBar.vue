<template>
    <div>
        <b-col class="sm-3">
        <b-form-select v-model="areaCode" :options="sidos" @change="gugunList"></b-form-select>
        </b-col>
        <b-col class="sm-3">
        <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
        </b-col>
        <select id="search-content-id" class="form-select me-2" name="contentTypeId">
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
        </select>
        <input
            id="search-keyword"
            name="keyword"
            class="form-control me-2"
            type="search"
            placeholder="검색어"
            aria-label="검색어"
            v-model="keyword"
        />
        <a id="btn-search" class="btn btn-primary" type="button" @click="search">
            검색
        </a>
    </div>
</template>

<script>
let serviceKey = "y2%2BUi4b8CxQtnL3vC%2BOZriRWskhHYtKj%2FrZc870HXKbp2MJVPZYwC7FENHv6VZCW8OC1DaHDfjE7yOwb4k3Rzw%3D%3D";     
export default {
    components: {},
    data() {
        return {
            sidos: [{ value: 0, text: "선택하세요" }],
            areaCode: '0',
            gugunCode: '0',
            guguns:[{ value: 0, text: "선택하세요" }],
            keyword: '',
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
            .then((data)=>{
                let areas = [...data.response.body.items.item];
                areas.forEach((area)=>{
                    this.sidos.push({value:area.code, text: area.name});
                })
            });
        // console.log(areas);

    },
    methods: {
        gugunList(){
            this.guguns =[{ value: 0, text: "선택하세요" }];
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
                    console.log(areas)
                    areas.forEach((area)=>{
                        this.guguns.push({value:area.code, text: area.name});
                        })
                    });
        },
        search(){

        },

    },
};
</script>

<style scoped></style>