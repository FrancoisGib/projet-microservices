export default interface UserPrincipal {
  id: bigint;
  username: string;
  organizationId?: number;
  organizationName?: string;
}
