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
            <v-btn @click="addItem">Add Item</v-btn>
            <v-btn @click="expCSV">Export CSV</v-btn>
            <v-btn @click="expPDF">Export PDF</v-btn>
            <v-btn @click="switchView">Switch to users</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="items"
                :search="search"
                @click:row="editItem"
        ></v-data-table>
        <ItemDialogAdmin
                :opened="dialogVisible"
                :item="selectedItem"
                @refresh="refreshList"
        ></ItemDialogAdmin>
    </v-card>
</template>

<script>

import api from "../api";
import ItemDialogAdmin from "../components/ItemDialogAdmin";
import router from "../router";

export default {
    name: "ItemListAdmin",
    components: { ItemDialogAdmin },
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
        editItem(item) {
            this.selectedItem = item;
            this.dialogVisible = true;
        },
        addItem() {
            this.dialogVisible = true;
        },
        async refreshList() {
            this.dialogVisible = false;
            this.selectedItem = {};
            this.items = await api.items.allItems();
        },
        expCSV(){
            api.users.exportCSV()
        },
        expPDF(){
            api.users.exportPDF()
        },
        switchView(){
            router.push("/users");
        },
    },
    created() {
        this.refreshList();
    },
};

</script>

<style scoped></style>
