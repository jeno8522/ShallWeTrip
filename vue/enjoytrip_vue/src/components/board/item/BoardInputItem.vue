<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userId-group"
          label="작성자:"
          label-for="userId"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userId"
            :disabled="true"
            v-model="userInfo.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="dark" class="m-1" v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="dark" class="m-1" v-else>글수정</b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";

const memberStore = "memberStore";
export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        infoId: 0,
        userId: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  created() {
    console.log(this.type);
    if (this.type === "modify") {
      http.get(`/board/${this.$route.params.infoId}`).then(({ data }) => {
        // this.article.infoId = data.article.infoId;
        // this.article.userId = data.article.userId;
        // this.article.title = data.article.title;
        // this.article.content = data.article.content;
        this.article = data;
      });
      this.article.userId = this.userInfo.userid;
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      // !this.article.userId &&
      //   ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userId.focus());
      // err &&
      !this.article.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.infoId = 0;
      this.article.title = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      http
        .post(`/board`, {
          userId: this.userInfo.userid,
          title: this.article.title,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyArticle() {
      http
        .put(`/board`, {
          infoId: this.article.infoId,
          userId: this.userInfo.userid,
          title: this.article.title,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style></style>
