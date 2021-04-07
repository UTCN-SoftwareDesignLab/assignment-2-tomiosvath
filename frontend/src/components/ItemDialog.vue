<template>
  <v-dialog
          transition="dialog-bottom-transition"
          max-width="300"
          :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>Sell Book</v-toolbar>
        <v-card-actions>
          <v-btn @click="sellItem">Sell Book</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ItemDialog",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    sellItem(){
      api.items.sell({
        id: this.item.id,
      })
      .then(() => this.$emit("refresh"));
    },
  },
  computed: {},
};
</script>

<style scoped></style>
