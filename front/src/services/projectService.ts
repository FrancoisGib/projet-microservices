import axios from "../axios.config";
import Page from "../interfaces/Page";
import Project from "../interfaces/Project";
import userInformationService from "./userInformationService";

const projectService = {
  getProjects: async (): Promise<Project[]> => {
    const response = await axios.get<Project[]>("/projects");
    return response.data;
  },

  getUserProjectsStartingWithNamePaginated: async (
    name: string,
    pageNumber: number
  ): Promise<Page<Project>> => {
    const userId = userInformationService.getUserPrincipal()?.id;
    const response = await axios.get<Page<Project>>(
      `/users/projects?userId=${userId}&pageNumber=${pageNumber}&filterName=${name}`
    );
    return response.data;
  },
};

export default projectService;
