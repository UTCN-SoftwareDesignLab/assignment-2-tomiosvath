<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "Create item" : "Operations" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model="item.author" label="Author" />
                    <v-text-field v-model="item.title" label="Title" />
                    <v-text-field v-model="item.genre" label="Genre" />
                    <v-text-field v-model="item.quantity" label="Quantity" />
                    <v-text-field v-model="item.price" label="Price" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteItem">Delete Book</v-btn>
                    <v-btn @click="sellItem">Sell Book</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "ItemDialogAdmin",
        props: {
            item: Object,
            opened: Boolean,
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.items
                        .create({
                            author: this.item.author,
                            title: this.item.title,
                            genre: this.item.genre,
                            quantity: this.item.quantity,
                            price: this.item.price,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.items
                        .edit({
                            id: this.item.id,
                            author: this.item.author,
                            title: this.item.title,
                            genre: this.item.genre,
                            quantity: this.item.quantity,
                            price: this.item.price,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            deleteItem(){
                api.items.delete({
                    id: this.item.id,
                })
                    .then(() => this.$emit("refresh"));
            },
            sellItem(){
                api.items.sell({
                    id: this.item.id,
                })
                    .then(() => this.$emit("refresh"));
            },
        },
        computed: {
            isNew: function () {
                return !this.item.id;
            },
        },
    };
</script>

<style scoped></style>
