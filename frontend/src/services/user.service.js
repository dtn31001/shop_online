import authHeader from "./auth-header";
import axiosClient from "./axiosClient";

const UserApi = {
  getAll: () => {
    const API_URL = "/users";

    return axiosClient.get(API_URL, {
      headers: authHeader(),
      "Content-Type": "application/json",
    });
  },

  get: (id) => {
    const API_URL = `/users/${id}`;
    return axiosClient.get(API_URL, {
      headers: authHeader(),
      "Content-Type": "application/json",
    });
  },

  create: (data) => {
    const API_URL = "/users";
    return axiosClient.post(API_URL, data, {
      headers: authHeader(),
      "Content-Type": "multipart/form-data",
    });
  },

  update: (id, data) => {
    const API_URL = `/users/${id}`;
    return axiosClient.put(API_URL, data, {
      headers: authHeader(),
      "Content-Type": "multipart/form-data",
    });
  },

  delete: (id) => {
    const API_URL = `/users/${id}`;
    return axiosClient.delete(API_URL, {
      headers: authHeader(),
      "Content-Type": "application/json",
    });
  },
};

export default UserApi;
