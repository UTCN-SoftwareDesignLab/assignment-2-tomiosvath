<template>
  <v-card>
    <v-card-title>
      Books
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="items"
      :search="search"
      @click:row="editItem"
    ></v-data-table>
    <ItemDialog
      :opened="dialogVisible"
      :item="selectedItem"
      @refresh="refreshList"
    ></ItemDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ItemDialog from "../components/ItemDialog";
export default {
  name: "ItemList",
  components: { ItemDialog },
  data() {
    return {
      items: [],
      search: "",
      headers: [
        {
          text: "author",
          align: "start",
          sortable: false,
          value: "author",
        },
        { text: "title", value : "title"},
        { text: "genre", value: "genre" },
        { text: "price", value: "price"},
        { text: "quantity", value: "quantity"},
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.items = await api.items.allItems();
    },
    editItem(item) {
      this.selectedItem = item;
      this.dialogVisible = true;
    },
  },
  created() {
    this.refreshList();
  },
};

</script>

<style scoped></style>
