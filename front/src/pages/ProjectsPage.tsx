import { useQuery } from "react-query";
import organizationService from "../features/organizations/organizationService";
import NavBarLayout from "../components/navbar/NavBarLayout";

export default function ProjectsPage() {
  const { data, isLoading, isError } = useQuery(["organizations"], () =>
    organizationService.getUsers()
  );

  return (
    <>
      <NavBarLayout>
        <p>{JSON.stringify(data)}</p>
        {(isLoading && <p>Loading...</p>) || (isError && <p>Error</p>)}
      </NavBarLayout>
    </>
  );
}
