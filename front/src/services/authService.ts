import axios from "../axios.config";
import UserPrincipal from "../interfaces/UserPrincipal";

interface AuthRequest {
  username: string;
  password: string;
}

const authService = {
  login: async ({ username, password }: AuthRequest): Promise<UserPrincipal> =>
    axios.post<AuthRequest, UserPrincipal>("/login", { username, password }),
};

export default authService;
