import { useQuery } from "react-query";
import projectService from "../services/projectService";

import Grid from "@mui/material/Grid";
import Skeleton from "@mui/material/Skeleton";
import { Box } from "@mui/material";

export default function ProjectsPage() {
  const { data, isLoading, isError } = useQuery(["projects"], () =>
    projectService.getProjects()
  );

  return (
    <>
      <p>{JSON.stringify(data)}</p>
      <Grid container wrap="nowrap" display="flex" justifyContent="center">
        {!isLoading
          ? Array.from(new Array(3)).map(() => (
              <Box margin="10px">
                <Skeleton variant="rounded" width={500} height={300} />
              </Box>
            ))
          : null}
      </Grid>
      <Box bgcolor="black" width="200px" height="100px"></Box>
    </>
  );
}
