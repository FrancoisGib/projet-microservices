import { AxiosResponse } from "axios";
import axios from "../axios.config";
import UserPrincipal from "../interfaces/UserPrincipal";

interface AuthRequest {
  username: string;
  password: string;
}

const authService = {
  login: async ({
    username,
    password,
  }: AuthRequest): Promise<AxiosResponse<UserPrincipal>> =>
    axios.post<UserPrincipal>("/login", { username, password }),
};

export default authService;
