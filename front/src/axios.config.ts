import axiosClient from "axios";

const axios = axiosClient.create({
  baseURL: process.env.REACT_APP_API_GATEWAY_URL,
});

export default axios;
