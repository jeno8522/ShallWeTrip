<template>
  <b-container class="main-container" fluid>
    <b-carousel
      id="main-carousel"
      :interval="3000"
      img-width="1024"
      img-height="480"
      controls
      indicators
      style="text-shadow: 1px 1px 2px #333"
    >
      <b-carousel-slide class="main-carousel" img-src="/assets/images/main4.jpg"></b-carousel-slide>
      <b-carousel-slide class="main-carousel" img-src="/assets/images/main1.png"></b-carousel-slide>
      <b-carousel-slide class="main-carousel" img-src="/assets/images/main2.png"></b-carousel-slide>
      <span class="carousel-text"><string> SHALL WE TRIP? </string></span>
      <!-- <span class="carousel-subtext">SHALL WE TRIP?</span> -->
    </b-carousel>

    <div class="main-content">
      <div class="main-element">
        <router-link :to="{ name: 'attraction' }" class="link">
          <img src="/assets/images/main-bt-001.jpg" alt="" />
          <span class="image-text">관광지 조회하기</span>
        </router-link>
      </div>
      <div class="main-element">
        <router-link :to="{ name: 'hotplace' }" class="m-2 link">
          <img src="/assets/images/main-bt-002.jpg" alt="" />
          <span class="image-text">핫플레이스 가기</span>
        </router-link>
      </div>
      <div class="main-element">
        <router-link :to="{ name: 'board' }" class="m-2 link">
          <img src="/assets/images/main-bt-003.jpg" alt="" />
          <span class="image-text">게시판 가기</span>
        </router-link>
      </div>
    </div>
  </b-container>
</template>

<script>
export default {
  name: "AppMain",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Anton&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Libre+Barcode+128+Text&family=M+PLUS+1p:wght@900&display=swap");

.carousel-text {
  display: flex;
  /* align-items: center; */
  /* justify-content: center; */
  /* flex-direction: column; */
  /* text-align: center; */
  /* font-family: "Anton", sans-serif; */
  font-family: "Gowun Dodum", sans-serif;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 120px;
}
.carousel-subtext {
  position: absolute;
  font-family: "Gowun Dodum", sans-serif;
  top: 80%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 30px;
}
.image-text {
  font-size: 70px;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
.main-container {
  padding: 0;
}
.main-content {
  /* margin: 10%;
  padding: 10%; */
  justify-content: center;
  align-content: center;
}
.main-element {
  margin-top: 10%;
  margin-bottom: 10%;
  position: relative;
}
.main-carousel {
  height: 800px;
  position: relative;
}
.image-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 18px;
  font-weight: bold;
}
/* .carousel-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 80px;
} */
/* #main-carousel {
  height: 5vh;
} */
</style>
