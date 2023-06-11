<template>
  <div>
    <!-- <b-row class="m-2"> -->
    <!-- <b-col cols="10" class="align-self-center"></b-col> -->
    <div class="row">
      <!-- <div class="table-responsive mt-3 mb-3 col-sm-2 col-md-6"> -->
      <div>
        <!-- <table class="table table-striped" style="display: none"> -->
        <b-table-simple class="table table-striped" :sticky-body="false" sticky-header="700px">
          <b-thead>
            <b-tr>
              <b-th>대표이미지</b-th>
              <b-th>관광지명</b-th>
              <b-th>주소 </b-th>
              <b-th>전화번호 </b-th>
            </b-tr>
          </b-thead>
          <b-tbody id="trip-list">
            <b-tr v-for="(attraction, index) in attractions" :key="index">
              <b-td><img :src="attraction.image" width="100px" /></b-td>
              <b-td>{{ attraction.title }}</b-td>
              <b-td>{{ attraction.addr1 }}</b-td>
              <b-td>{{ attraction.tel }}</b-td>
              <b-td>
                <div>
                  <b-button @click="showModal(attraction)" variant="dark">상세보기</b-button>
                </div>
              </b-td>
            </b-tr>
          </b-tbody>
        </b-table-simple>
        <b-modal id="modal-lg" size="lg" title="Large Modal">Hello Large Modal!</b-modal>
      </div>

      <!-- 관광지 검색 end -->
    </div>
  </div>
</template>

<script>
export default {
  // components: {},
  data() {
    return {
      message: "",
      boxOne: "",
    };
  },
  props: {
    attractions: [],
  },
  watch: {
    attractions() {},
  },
  computed: {},
  created() {},
  mounted() {},
  methods: {
    showModal(attraction) {
      console.log(attraction);
      const h = this.$createElement;
      // Using HTML string
      const titleVNode = h("div", { domProps: { innerHTML: attraction.title } });
      // More complex structure
      const messageVNode = h("div", { class: ["foobar"] }, [
        h("p", { class: ["text-center"] }, [
          h("strong", "주소 : "),
          attraction.addr1,
          attraction.addr2,
        ]),
        h("p", { class: ["text-center"] }, [h("strong", "전화번호 : "), attraction.tel]),
        h("b-img", {
          props: {
            src: attraction.image,
            thumbnail: true,
            center: true,
            fluid: true,
            // rounded: 'circle'
          },
        }),
        h("p", { class: ["text-center"] }, [h("strong", " ")]),
        h("p", { class: ["text-center"] }, [h("strong", "상세 설명 : "), attraction.overview]),
      ]);
      // We must pass the generated VNodes as arrays
      this.$bvModal.msgBoxOk([messageVNode], {
        title: [titleVNode],
        buttonSize: "sm",
        centered: true,
        size: "lg",
      });
    },
  },
};
</script>

<style scoped>
#trip-list {
  max-height: calc(100vh - 300px);
  overflow-y: auto;
}
.table-striped {
  width: 95%;
  height: 5000px;
}
</style>
