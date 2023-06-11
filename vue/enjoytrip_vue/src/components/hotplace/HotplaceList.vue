<template>
  <b-container class="bv-example-row mt-3 hotplace">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-dark" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <!-- <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          @row-clicked="viewArticle"
        >
        </b-table>
      </b-col> -->
      <b-card-group deck v-for="(item, index) in articles" :key="index">
        <b-card
          :title="item.title"
          :img-src="item.fileInfos[0].saveFile | urlTo"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem"
          class="mb-2"
          @click="viewArticle(item)"
        >
          <b-card-text>
            {{ item.content }}
          </b-card-text>
          <small class="text-muted">조회수 : {{ item.hit }}</small>
        </b-card>
      </b-card-group>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  name: "HotplaceList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "hotId", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "createDate", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],

      currentPage: 1,
      perPage: 10,
    };
  },
  computed: {
    rows() {
      return this.articles.length;
    },
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
    // perPage() {
    //   this.currentPage / 5;
    // },
  },
  created() {
    http.get(`/hotplace`).then(({ data }) => {
      this.articles = data;
      // console.log(this.articles);
    });
    // http.get(`/hotplace/list?page=${this.pageNo}`).then(({ data }) => {
    //   this.articles = data;
    //   // console.log(this.articles);
    // });
  },
  methods: {
    moveWrite() {
      if (!this.userInfo) {
        alert("로그인 해주세요!");
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({ name: "hotplacewrite" });
      }
    },
    viewArticle(article) {
      if (!this.userInfo) {
        alert("로그인 해주세요!");
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({
          name: "hotplaceview",
          params: { hotId: article.hotId },
        });
      }
    },
    movePage(button, page) {
      this.currentPage = page;
      this.getNoticeListByPage(page);
    },
    getNoticeListByPage: function (page) {
      var limit = 20;
      var offset = (page - 1) * limit;

      this.loading = true;
      this.$http
        .get("/notice/list" + `?offset=${offset}&limit=${limit}`)
        .then((response) => {
          this.items = response.data;
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => (this.loading = false));
    },
  },
  filters: {
    urlTo(url) {
      return `http://localhost:9999/trip/upload/image/${url}`;
    },
  },
};
</script>

<style scope>
.hotplace img {
  max-height: 300px;
  max-width: 19rem;
}
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.hotplacePageNavigation {
  justify-content: center;
  align-content: center;
}
</style>
