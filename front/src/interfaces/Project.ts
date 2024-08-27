export default interface Project {
  id: number;
  name: string;
  organization?: string;
  usersId: bigint[];
  scope: string;
  description: string;
}
