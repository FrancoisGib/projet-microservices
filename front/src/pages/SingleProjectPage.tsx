import { useParams } from "react-router-dom";
import NavBarLayout from "../components/navbar/NavBarLayout";
import BasicCard from "../components/projects/ProjectCard";

export default function () {
  const { name } = useParams();
  return (
    <NavBarLayout>
      <p>{name}</p>
      <BasicCard />
    </NavBarLayout>
  );
}
