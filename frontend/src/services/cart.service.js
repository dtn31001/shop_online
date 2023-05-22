import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/carts";

const getAll = () => {
  return axios.get(API_URL, { headers: authHeader() });
};

const get = (id) => {
  return axios.get(API_URL + `/${id}`, { headers: authHeader() });
};

const create = (data) => {
  return axios.post(API_URL, data, { headers: authHeader() });
};

const update = (id, data) => {
  return axios.put(API_URL + `/${id}`, data, {
    headers: authHeader(),
  });
};

const deleteCart = (id) => {
  return axios.delete(API_URL + `/${id}`, {
    headers: authHeader(),
  });
};

// const payAll = (sumAmount) => {
//   const data = {};
//   return axios.put(API_URL + `/payment/${sumAmount}`, data, {
//     headers: authHeader(),
//   });
// };

// const findByTitle = (title) => {
//   return axios.get(API_URL + `/carts?title=${title}`, {
//     headers: authHeader(),
//   });
// };

export default {
  getAll,
  get,
  create,
  update,
  deleteCart,
  //  payAll,
  //   findByTitle,
};
