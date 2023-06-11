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
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="userpwd-group"
          label="비밀번호:"
          label-for="userpwd"
          description="비밀번호을 입력하세요."
        >
          <b-form-input
            id="userpwd"
            v-model="user.userpwd"
            type="password"
            required
            placeholder="비밀번호 입력..."
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

        <b-button type="submit" variant="outline-dark" class="m-1">회원가입</b-button>

        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

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
    onReset(event) {
      event.preventDefault();
      this.user.userid = "";
      this.user.userpwd = "";
      this.user.name = "";
      this.user.email = "";
      this.user.nickname = "";
      this.moveList();
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
          let msg = "이미 등록된 아이디입니다.";
          console.log(data);
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            this.moveList();
          } else {
            alert(msg);
          }
        });
    },
    modifyUser() {
      console.log("modify");
      console.log(this.user);
      http.put(`/user/update`, this.user).then(({ data }) => {
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
      this.$router.push({ name: "main" });
    },
  },
};
</script>

<style></style>
