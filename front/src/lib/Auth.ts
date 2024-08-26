import userInformationService from "../services/userInformationService";

export const isAuthenticated = (): boolean =>
  userInformationService.getUserPrincipal() !== null;
