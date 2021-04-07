import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allUsers() {
    return HTTP.get(BASE_URL + "/user", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  exportCSV(){
    return HTTP.get(BASE_URL + "/fo/export/CSV", { headers: authHeader() }).then();
  },
  exportPDF(){
    return HTTP.get(BASE_URL + "/fo/export/PDF", { headers: authHeader() }).then();
  },
  create(user) {
    return HTTP.post(BASE_URL + "/user", user,{ headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
  delete(user){
    return HTTP.delete(BASE_URL + "/user/" + user.id, { headers: authHeader() }).then();
  },
  edit(user) {
    return HTTP.patch(BASE_URL + "/user", user,{ headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
};
