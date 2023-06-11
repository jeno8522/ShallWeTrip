<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <!-- <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col> -->
      <b-col>
        <b-alert variant="secondary" show><h3>글 작성</h3></b-alert>
      </b-col>
    </b-row>
    <hotplace-input-item type="register" />
  </b-container>
</template>

<script>
import HotplaceInputItem from "@/components/hotplace/item/HotplaceInput";

export default {
  name: "HotplaceWrite",
  components: {
    HotplaceInputItem,
  },
};
</script>

<style></style>
