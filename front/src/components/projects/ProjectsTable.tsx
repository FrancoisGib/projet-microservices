import { TableFooter, TablePagination } from "@mui/material";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Page from "../../interfaces/Page";
import Project from "../../interfaces/Project";
import router from "../../routes/Router";

export default function ProjectsTable({
  projects,
  pageNumber,
  setPageNumber,
}: {
  projects: Page<Project>;
  pageNumber: number;
  setPageNumber: React.Dispatch<React.SetStateAction<number>>;
}) {
  return (
    <TableContainer
      component={Paper}
      className="my-4 flex items-center justify-center"
      sx={{
        width: "90%",
      }}
    >
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="center">Id</TableCell>
            <TableCell align="center">Name</TableCell>
            <TableCell align="center">Description</TableCell>
            <TableCell align="center">Organization</TableCell>
            <TableCell align="center">Users count</TableCell>
            <TableCell align="center">Scope</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {projects.content.map((project) => (
            <TableRow
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
                "&.Mui-selected": { backgroundColor: "white" },
              }}
              key={project.id}
              selected
              component="div"
              onClick={() => router.navigate(`/projects/${project.id}`)}
            >
              <TableCell align="center" component="th" scope="row">
                {project.id}
              </TableCell>
              <TableCell align="center">{project.name}</TableCell>
              <TableCell align="center">{project.description}</TableCell>
              <TableCell align="center">
                {project.organization ?? "None"}
              </TableCell>
              <TableCell align="center">{project.usersId.length}</TableCell>
              <TableCell align="center">{project.scope}</TableCell>
            </TableRow>
          ))}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TablePagination
              className="border-solid border-t"
              count={projects.totalElements}
              rowsPerPage={10}
              page={pageNumber}
              labelRowsPerPage=""
              rowsPerPageOptions={[]}
              onPageChange={(_, page) => setPageNumber(page)}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </TableContainer>
  );
}
