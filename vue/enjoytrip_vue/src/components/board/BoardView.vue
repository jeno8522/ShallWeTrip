<template>
  <b-container class="bv-example-row mt-3">
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
          :header-html="`<h3>${article.infoId}.
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
  name: "BoardDetail",
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
    http.get(`/board/${this.$route.params.infoId}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { infoId: this.article.infoId },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.infoId}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { infoId: this.article.infoId },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(createDate) {
  //     return moment(new Date(createDate)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
