import UserPrincipal from "../interfaces/UserPrincipal";

const userInformationService = {
  setUserPrincipal: (userPrincipal: UserPrincipal): void =>
    localStorage.setItem("user", JSON.stringify(userPrincipal)),

  getUserPrincipal: (): UserPrincipal | null => {
    const userPrincipalString = localStorage.getItem("user");
    if (userPrincipalString) {
      return JSON.parse(userPrincipalString);
    }
    return null;
  },
};
export default userInformationService;
