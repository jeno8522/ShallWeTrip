<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-form-input
        class="search-input"
        type="text"
        placeholder="검색어를 입력하세요"
        v-model="searchTerm"
      />
      <b-col class="text-right">
        <b-button variant="outline-dark" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="filteredItems"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          @row-clicked="viewArticle"
          ref="articleTable"
          :filter="searchTerm"
          :filter-included-fields="['title', 'userId']"
        >
          <template #cell(title)="data">
            <router-link :to="{ name: 'boardview', params: { infoId: data.item.infoId } }">
              <span class="title-text">{{ data.item.title }}</span>
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <b-row class="boardPageNavigation">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      >
        <template #first-text><span class="text-dark">First</span></template>
        <template #prev-text><span class="text-dark">Prev</span></template>
        <template #next-text><span class="text-dark">Next</span></template>
        <template #last-text><span class="text-dark">Last</span></template>
      </b-pagination>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      searchTerm: "",
      fields: [
        { key: "infoId", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "createDate", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],

      currentPage: 1,
      perPage: 5,
    };
  },
  computed: {
    rows() {
      return this.articles.length;
    },
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),

    filteredItems() {
      if (this.searchTerm) {
        console.log(this.articles);
        const searchTermLowerCase = this.searchTerm.toLowerCase();
        return this.articles.filter((item) => {
          return (
            item.title.toLowerCase().includes(searchTermLowerCase) ||
            item.userId.toLowerCase().includes(searchTermLowerCase)
          );
        });
      } else {
        return this.articles;
      }
    },
    // perPage() {
    //   this.currentPage / 5;
    // },
  },
  created() {
    http.get(`/board`).then(({ data }) => {
      this.articles = data;
      this.backup = this.articles;
      // console.log(this.articles);
    });
    // http.get(`/board/list?page=${this.pageNo}`).then(({ data }) => {
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
        this.$router.push({ name: "boardwrite" });
      }
    },
    viewArticle(article) {
      if (!this.userInfo) {
        alert("로그인 해주세요!");
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({
          name: "boardview",
          params: { infoId: article.infoId },
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
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.boardPageNavigation {
  justify-content: center;
  align-content: center;
}
.search-input {
  width: 200px;
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
}
.title-text {
  text-decoration: none;
  color: #191825;
}
</style>
