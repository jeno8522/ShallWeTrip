<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4 mb-4 text-center">
      <b-col>
        <todo-list-item v-for="(todo, index) in todos" :key="index" :todo="todo" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapState} from "vuex";
import TodoListItem from "@/components/todo/TodoListItem";

export default {
  name: "TodoList",
  components: {
    TodoListItem,
  },
  computed: {
    // todos(){
    //   return this.$store.state.todo;
    // }
    ...mapState(["todos"]),
  },
};
</script>

<style></style>
