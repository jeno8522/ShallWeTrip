<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">회원 정보</h3>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppUser",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 191, 138, 0.3) 30%);
  font-size: 250%;
}
</style>
