import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allItems() {
    return HTTP.get(BASE_URL + "/fo", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(item) {
    return HTTP.post(BASE_URL + "/fo", item, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(item) {
    return HTTP.patch(BASE_URL + "/fo", item,{ headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(item){
    return HTTP.delete(BASE_URL + "/fo/" + item.id, { headers: authHeader() }).then(
    );
  },
  sell(item){
    return HTTP.patch(BASE_URL + "/fo/sell", item,{ headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
};
