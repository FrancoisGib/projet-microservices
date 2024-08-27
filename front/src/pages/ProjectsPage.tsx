import { useQuery } from "react-query";
import projectService from "../services/projectService";

import { useState } from "react";
import ProjectsTable from "../components/projects/ProjectsTable";
import SearchBar from "../components/SearchBar";

export default function ProjectsPage() {
  const [searchField, setSearchField] = useState("");
  const [fetchState, setFetchState] = useState("");
  const [pageNumber, setPageNumber] = useState(0);

  const {
    data: projects,
    isLoading,
    isError,
  } = useQuery(
    ["projects", fetchState, pageNumber],
    () =>
      projectService.getUserProjectsStartingWithNamePaginated(
        fetchState,
        pageNumber
      ),
    { retryOnMount: false, staleTime: 60000 }
  );

  return (
    <div className="flex justify-center w-full">
      <div id="page-container" className="w-[90%] my-8">
        {(isLoading && <div>Loading...</div>) ||
          (isError && <div>Error: Unable to fetch data</div>) || (
            <>
              <SearchBar
                setSearchField={setSearchField}
                searchField={searchField}
                setFetchState={setFetchState}
              />
              {projects && (
                <ProjectsTable
                  projects={projects}
                  pageNumber={pageNumber}
                  setPageNumber={setPageNumber}
                />
              )}
            </>
          )}
      </div>
    </div>
  );
}
