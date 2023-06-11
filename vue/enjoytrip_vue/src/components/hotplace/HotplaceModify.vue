<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>글 수정</h3></b-alert>
      </b-col>
    </b-row>
    <hotplace-input-item type="modify" />
  </b-container>
</template>

<script>
import HotplaceInputItem from "@/components/hotplace/item/HotplaceInputItem";

export default {
  name: "HotplaceModify",
  components: {
    HotplaceInputItem,
  },
};
</script>

<style></style>
