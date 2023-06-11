<template>
  <b-container class="bv-example-row mt-3 hotplace">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>글 보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-dark" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          v-if="userInfo && (article.userId === userInfo.userid || userInfo.userid === 'admin')"
          variant="outline-dark"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button
          v-if="userInfo && (article.userId === userInfo.userid || userInfo.userid === 'admin')"
          variant="outline-danger"
          size="sm"
          @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.hotId}.
          ${article.title}</h3>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-list-group flush>
            <b-list-group-item> 작성자 : {{ article.userId }} </b-list-group-item>
            <!-- <b-row> -->
            <b-list-group-item>
              작성일 : {{ article.createDate }} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 조회수: ❤ {{ article.hit }}</b-list-group-item
            >
            <!-- <b-list-group-item> 조회수 : {{ article.hit }} </b-list-group-item> -->
            <!-- </b-row> -->
          </b-list-group>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
          <tr>
            <td>
              <img
                v-for="(file, index) in article.fileInfos"
                :key="index"
                :src="file.saveFile | urlTo"
              />
            </td>
          </tr>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";
const memberStore = "memberStore";
export default {
  name: "HotplaceDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/hotplace/${this.$route.params.hotId}`).then(({ data }) => {
      this.article = data;
      console.log(this.article);
    });
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "hotplacemodify",
        params: { hotId: this.article.hotId },
      });
      //   this.$router.push({ path: `/hotplace/modify/${this.article.hotId}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "hotplacedelete",
          params: { hotId: this.article.hotId },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "hotplacelist" });
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
</style>
