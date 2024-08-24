import axios from "../../axios.config";

interface Organization {
  name: string;
}

const organizationService = {
  getUsers: async (): Promise<Organization[]> => {
    const response = await axios.get<any[]>("/projects");
    return response.data;
  },
};

export default organizationService;
