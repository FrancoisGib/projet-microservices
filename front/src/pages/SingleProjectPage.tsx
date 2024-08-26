import { useParams } from "react-router-dom";
import BasicCard from "../components/projects/ProjectCard";

export default function () {
  const { name } = useParams();
  return (
    <>
      <p>{name}</p>
      <BasicCard />
    </>
  );
}
