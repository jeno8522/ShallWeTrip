<template>
  <div>
    <b-navbar toggleable="lg" type="light" style="background-color: #f5f7f8">
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'main' }" class="link">
          <span class="nav-Maintext">SHALLWETRIP</span>
          <!-- <img src="../assets/images/logo.png" style="height: 50px" /> -->
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link :to="{ name: 'attraction' }" class="link">
              <!-- <b-icon
                icon="calendar-check"
                class="nav-icon"
                animation="fade"
                font-scale="2"
              ></b-icon> -->
              <span class="nav-text">관광지 </span>
            </router-link>
            <router-link :to="{ name: 'hotplace' }" class="m-2 link">
              <!-- <b-icon
                icon="hand-thumbs-up"
                class="nav-icon"
                animation="fade"
                font-scale="2"
              ></b-icon> -->
              <span class="nav-text">핫플레이스</span>
            </router-link>
            <router-link :to="{ name: 'board' }" class="m-2 link">
              <!-- <b-icon class="nav-icon" icon="journal" animation="fade" font-scale="2"></b-icon> -->
              <span class="nav-text">게시판</span>
            </router-link>
            <!-- <router-link :to="{ name: 'house' }" class="m-2 link">
              <span class="nav-text">아파트정보</span>
            </router-link>
            <router-link :to="{ name: 'todo' }" class="link">
              <span class="nav-text">TodoList</span>
            </router-link> -->
          </b-nav-item>
        </b-navbar-nav>

        <!-- after login -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center">
            <!-- <b-avatar src="https://placekitten.com/300/300"></b-avatar> -->
            <!-- <b-avatar rounded="sm" ></b-avatar> -->
            <span class="nav-text"
              ><strong>{{ userInfo.nickname }} </strong>님 환영합니다.</span
            >
          </b-nav-item>
          <b-nav-item class="align-self-center">
            <router-link :to="{ name: 'mypage' }" class="link align-self-center"
              ><span class="nav-text">내정보보기</span></router-link
            >
          </b-nav-item>
          <b-nav-item class="align-self-center link" @click.prevent="onClickLogout"
            ><span class="nav-text">로그아웃</span></b-nav-item
          >
        </b-navbar-nav>
        <!-- before login -->
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'join' }" class="link">
                <b-icon icon="person-circle" variant="dark"></b-icon> 회원가입
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'login' }" class="link">
                <b-icon icon="key" variant="dark"></b-icon> 로그인
              </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Libre+Barcode+128+Text&display=swap");

#logo {
  width: 120px;
}
.navbar-toggler {
  border-color: lightblue;
}
.link {
  text-decoration: none;
}
.nav-text {
  color: #191825;
}

.nav-Maintext {
  color: #191825;
  font-size: 40px;
  font-family: "Libre Barcode 128 Text", cursive;
}
.nav-text:hover {
  color: #9db2bf;
}
.nav-icon {
  color: #191825;
}
.nav-icon:hover {
  color: #9db2bf;
}
/* b-nav-text {
  color: #526d82;
}
b-nav-text:hover {
  color: #89bfef;
} */
</style>
