<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <user-input-item type="join"></user-input-item>
  </b-container>
</template>

<script>
import UserInputItem from "@/components/user/item/UserInputItem";
export default {
  name: "UserRegister",
  components: {
    UserInputItem,
  },
};
</script>

<style></style>
