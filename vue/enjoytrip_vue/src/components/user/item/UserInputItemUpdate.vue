<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="ID:"
          label-for="userid"
          description="ID를 입력하세요."
        >
          <b-form-input
            id="userid"
            v-model="user.userid"
            type="text"
            required
            placeholder="ID 입력..."
            ref="userid"
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userpwd-group"
          label="비밀번호:"
          label-for="useroldpwd"
          description="비밀번호을 입력하세요."
        >
          <b-form-input
            id="useroldpwd"
            v-model="olduserpwd"
            type="password"
            required
            placeholder="비밀번호 입력..."
            ref="userpwd"
          ></b-form-input>
          <b-button :variant="chechform" class="m-1" @click="onCheck">비밀번호 확인</b-button>
        </b-form-group>

        <b-form-group
          id="userpwd-group"
          label="새로운 비밀번호:"
          label-for="userpwd"
          description="새 비밀번호을 입력하세요."
        >
          <b-form-input
            id="newuserpwd"
            v-model="user.userpwd"
            type="password"
            required
            placeholder="새 비밀번호 입력..."
            ref="userpwd"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="name-group"
          label="이름:"
          label-for="name"
          description="이름을 입력하세요."
        >
          <b-form-input
            id="name"
            v-model="user.name"
            type="text"
            required
            placeholder="이름 입력..."
            ref="name"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="email-group"
          label="이메일:"
          label-for="email"
          description="이메일을 입력하세요."
        >
          <b-form-input
            id="email"
            v-model="user.email"
            type="email"
            required
            placeholder="이메일 입력..."
            ref="email"
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="nickname-group"
          label="닉네임:"
          label-for="nickname"
          description="닉네임을 입력하세요."
        >
          <b-form-input
            id="nickname"
            v-model="user.nickname"
            type="text"
            required
            placeholder="닉네임 입력..."
            ref="nickname"
          ></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="outline-dark" class="m-1">정보 수정</b-button>

        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserInputItem",
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
        name: "",
        email: "",
        nickname: "",
      },
      olduserpwd: "",
      checkPW: "false",
      chechform: "outline-danger",
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    console.log(this.type);
    if (this.type === "usermodify") {
      console.log(this.$route.params.userid);
      this.user.userid = this.userInfo.userid;
      this.user.name = this.userInfo.name;
      this.user.email = this.userInfo.email;
      this.user.nickname = this.userInfo.nickname;
    }
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),

    onClickLogout() {
      console.log(this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userid && ((msg = "ID 입력해주세요"), (err = false), this.$refs.userid.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호 입력해주세요"), (err = false), this.$refs.userpwd.focus());
      err &&
        !this.user.name &&
        ((msg = "이름 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.user.email &&
        ((msg = "이메일 입력해주세요"), (err = false), this.$refs.email.focus());
      err &&
        !this.user.nickname &&
        ((msg = "닉네임 입력해주세요"), (err = false), this.$refs.nickname.focus());

      if (!err) alert(msg);
      else this.type === "join" ? this.registArticle() : this.modifyUser();
    },
    onCheck() {
      console.log("oncheck 들어옴");
      if (this.olduserpwd != "") {
        http.get(`/user/check/${this.user.userid}/${this.olduserpwd}`).then(({ data }) => {
          if (data === "fail") {
            alert("비밀번호가 일치하지 않습니다.");
          } else {
            this.checkPW = "true";
            this.chechform = "outline-dark";
            alert("비밀번호가 일치합니다.");
          }
        });
      }
    },

    onReset(event) {
      event.preventDefault();
      this.user.userpwd = "";
      this.user.name = "";
      this.user.email = "";
      this.user.nickname = "";
      //this.moveList();
    },
    registArticle() {
      console.log("resgist");
      http
        .post(`/user/regist`, {
          userid: this.user.userid,
          userpwd: this.user.userpwd,
          name: this.user.name,
          email: this.user.email,
          nickname: this.user.nickname,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          console.log(data);
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyUser() {
      if (this.checkPW == "false") {
        alert("비밀번호를 확인하세요");
      } else {
        http.put(`/user/update`, this.user).then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.onClickLogout();
          alert("다시 로그인 하세요");
        });
      }
    },
    moveList() {
      this.$router.push({ name: "main" });
    },
  },
};
</script>

<style></style>
