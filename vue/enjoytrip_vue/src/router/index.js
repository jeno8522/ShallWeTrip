import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
import AppHouse from "@/views/AppHouse";
import AppAttraction from "@/views/AppAttraction";
import store from "@/store";
Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/attraction",
    name: "attraction",
    component: AppAttraction,
  },

  {
    path: "/house",
    name: "house",
    component: AppHouse,
  },
  {
    path: "/todo",
    name: "todo",
    component: () => import("@/views/AppTodo"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "usermodify",
        name: "usermodify",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserModify"),
      },
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
      {
        path: "delete",
        name: "userdelete",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserDelete"),
      },
    ],
  },
  {
    path: "/hotplace",
    name: "hotplace",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppHotplace"),
    redirect: "/hotplace/list",
    children: [
      {
        path: "list",
        name: "hotplacelist",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/hotplace/HotplaceList"),
      },
      {
        path: "write",
        name: "hotplacewrite",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/hotplace/HotplaceWrite"),
      },
      {
        path: "view/:hotId",
        name: "hotplaceview",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/hotplace/HotplaceView"),
      },
      {
        path: "modify",
        name: "hotplacemodify",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/hotplace/HotplaceModify"),
      },
      {
        path: "delete/:hotId",
        name: "hotplacedelete",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/hotplace/HotplaceDelete"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardWrite"),
      },
      {
        path: "view/:infoId",
        name: "boardview",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardModify"),
      },
      {
        path: "delete/:infoId",
        name: "boarddelete",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardDelete"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
