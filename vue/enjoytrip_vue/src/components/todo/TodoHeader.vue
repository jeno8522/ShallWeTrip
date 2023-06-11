<template>
  <b-container class="bv-example-row">
    <b-row class="mt-1 text-center">
      <b-col> <h1>Todo it!</h1> </b-col>
    </b-row>
    <b-row class="mt-1 text-center">
      <b-col>
        <b-alert show variant="primary">할일 : {{allTodosCount}}</b-alert>
      </b-col>
      <b-col>
        <b-alert show variant="success">진행 : {{completedTodosCount}}</b-alert>
      </b-col>
      <b-col>
        <b-alert show variant="danger">완료 : {{unCompletedTodosCount}}</b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapGetters} from 'vuex';
export default {
  name: "TodoHeader",
  computed: {
    ...mapGetters(["allTodosCount","completedTodosCount","unCompletedTodosCount"])
  },
};
</script>

<style></style>
