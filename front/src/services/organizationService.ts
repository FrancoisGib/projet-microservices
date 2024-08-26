import axios from "../axios.config";

interface Organization {
  name: string;
}

const organizationService = {
  getUsers: async (): Promise<Organization[]> => {
    const response = await axios.get<Organization[]>("/organizations");
    return response.data;
  },
};

export default organizationService;
