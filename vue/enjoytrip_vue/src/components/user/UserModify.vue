<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <user-input-item type="usermodify"></user-input-item>
  </b-container>
</template>

<script>
import UserInputItem from "@/components/user/item/UserInputItemUpdate";
export default {
  name: "UserModify",
  components: {
    UserInputItem,
  },
};
</script>

<style></style>
