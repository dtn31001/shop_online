import authHeader from "./auth-header";
import axiosClient from "./axiosClient";

const ProductApi = {
  getAll: (params) => {
    const API_URL = "/products";
    const user = JSON.parse(localStorage.getItem("user"));
    if (user && user.token) {
      axiosClient.defaults.headers.common["Authorization"] =
        "Bearer " + user.token;
    }
    return axiosClient.get(
      API_URL,
      { params }
      // {
      //   headers: authHeader(),
      //   "Content-Type": "application/json",
      // }
    );
  },

  get: (id) => {
    const API_URL = `/products/${id}`;
    return axiosClient.get(API_URL, {
      headers: authHeader(),
      "Content-Type": "application/json",
    });
  },

  create: (data) => {
    const API_URL = "/products";
    return axiosClient.post(API_URL, data, {
      headers: authHeader(),
      "Content-Type": "multipart/form-data",
    });
  },

  update: (id, data) => {
    const API_URL = `/products/${id}`;
    return axiosClient.put(API_URL, data, {
      headers: authHeader(),
      "Content-Type": "multipart/form-data",
    });
  },

  delete: (id) => {
    const API_URL = `/products/${id}`;
    return axiosClient.delete(API_URL, {
      headers: authHeader(),
    });
  },
};

export default ProductApi;
