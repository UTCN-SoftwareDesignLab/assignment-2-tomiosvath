import authHeader, { BASE_URL, HTTP } from "../http";
import axios from "axios";

export default {
  allUsers() {
    return HTTP.get(BASE_URL + "/user", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  exportCSV(){
    //return HTTP.get(BASE_URL + "/fo/export/CSV", { headers: authHeader() }).then();
    return axios({
      url: BASE_URL + '/fo/files/CSV',
      method: 'GET',
      headers: authHeader(),
      responseType: 'blob',
    }).then((response) => {
      var fileURL = window.URL.createObjectURL(new Blob([response.data]));
      var fileLink = document.createElement('a');

      fileLink.href = fileURL;
      fileLink.setAttribute('download', 'report.csv');
      document.body.appendChild(fileLink);

      fileLink.click();
    });
  },
  exportPDF(){
    //return HTTP.get(BASE_URL + "/fo/files/image.jpg", { headers: authHeader() }).then();
    //return HTTP.get(BASE_URL + "/fo/export/PDF", { headers: authHeader() }).then();
    return axios({
      url: BASE_URL + '/fo/files/PDF',
      method: 'GET',
      headers: authHeader(),
      responseType: 'blob',
    }).then((response) => {
      var fileURL = window.URL.createObjectURL(new Blob([response.data]));
      var fileLink = document.createElement('a');

      fileLink.href = fileURL;
      fileLink.setAttribute('download', 'report.pdf');
      document.body.appendChild(fileLink);

      fileLink.click();
    });
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
