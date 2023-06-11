<template>
  <b-tr>
    <b-td>{{ hotId }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'hotplaceview', params: { hotId: hotId } }">{{
        title
      }}</router-link>
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ createDate | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "HotplaceListItem",
  props: {
    hotId: Number,
    userId: String,
    title: String,
    hit: Number,
    createDate: String,
  },
  filters: {
    dateFormat(createDate) {
      return moment(new Date(createDate)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
