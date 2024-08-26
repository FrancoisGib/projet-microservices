import axios from "../axios.config";

interface Project {
  name: string;
  usersId: bigint[];
  organizationId?: number;
}

const projectService = {
  getProjects: async (): Promise<Project[]> => {
    const response = await axios.get<Project[]>("/projects");
    return response.data;
  },
};

export default projectService;
