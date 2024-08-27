import axios from "../axios.config";
import Page from "../interfaces/Page";
import Project from "../interfaces/Project";

const projectService = {
  getProjects: async (): Promise<Project[]> => {
    const response = await axios.get<Project[]>("/projects");
    return response.data;
  },

  getProjectsStartingWithNamePaginated: async (
    name: string,
    pageNumber: number
  ): Promise<Page<Project>> => {
    const response = await axios.get<Page<Project>>(
      `/projects/like?name=${name}&page=${pageNumber}`
    );
    return response.data;
  },
};

export default projectService;
