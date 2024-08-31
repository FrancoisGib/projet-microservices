import { Button, ButtonGroup, Divider } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import ResourceCard from "../components/projects/ProjectCard";
import projectService from "../services/projectService";
import Project from "../interfaces/Project";

export default function () {
  const location = useLocation();
  const [project, setProject] = useState<Project>(location.state);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    const projectId = location.pathname.split("/")[2];
    if (!project) {
      projectService
        .getProjectById(Number(projectId))
        .then((res) => setProject(res))
        .catch((error) => setError(error.message));
    }
  }, []);

  return (
    <>
      <div className="flex flex-col items-center w-[100%]">
        <div
          style={{
            boxShadow: "var(--card-box-shadow)",
          }}
          id="project-informations-container"
          className="w-[100%] h-[30vh] min-h-[200px] flex items-center justify-center"
        >
          <div
            id="project-informations"
            className="w-[60%]"
            style={{
              display: "grid",
              gridTemplateColumns: "repeat(2, minmax(200px, 1fr))",
              gridTemplateRows: "repeat(3, 50px)",
            }}
          >
            <p>Id: {project?.id}</p>
            <p>Organization: {project?.organization}</p>
            <p>Nom: {project?.name}</p>
            <p>Description: {project?.description}</p>
            <p>Scope: {project?.scope}</p>
            <p>Nombre d'utilisateurs: {project?.users.length}</p>
          </div>
          {error && <p>Error: {error}</p>}
        </div>
        <ButtonGroup variant="text" className="w-[100%] ">
          <Button>Create Resource</Button>
          <Button>Add user to project</Button>
        </ButtonGroup>
        <Divider className="w-[100%]" />
        <ResourceCard />
      </div>
    </>
  );
}
