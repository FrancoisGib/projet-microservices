export default interface Project {
  id: number;
  name: string;
  organization?: string;
  users: any[];
  scope: string;
  description: string;
}
