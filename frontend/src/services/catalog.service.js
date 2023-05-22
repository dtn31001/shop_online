import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/catalogs";

const getAll = () => {
  return axios.get(API_URL, {
    headers: authHeader(),
    "Content-Type": "application/json",
  });
};

const get = (id) => {
  return axios.get(API_URL + `/${id}`, {
    headers: authHeader(),
    "Content-Type": "application/json",
  });
};

const create = (data) => {
  return axios.post(API_URL, data, {
    headers: authHeader(),
    "Content-Type": "application/json",
  });
};

const update = (id, data) => {
  return axios.put(API_URL + `/${id}`, data, {
    headers: authHeader(),
  });
};

const deleteCatalog = (id) => {
  return axios.delete(API_URL + `/${id}`, {
    headers: authHeader(),
  });
};

export default {
  getAll,
  get,
  create,
  update,
  deleteCatalog,
};
