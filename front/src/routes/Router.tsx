import { createBrowserRouter } from "react-router-dom";
import { isAuthenticated } from "../lib/Auth";
import LoginPage from "../pages/LoginPage";
import ProjectPage from "../pages/ProjectsPage";
import SingleProjectPage from "../pages/SingleProjectPage";
import ProtectedRoute from "./ProtectedRoute";

const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    element: <ProtectedRoute isAuthenticated={isAuthenticated()} />,
    children: [
      {
        path: "/projects",
        element: <ProjectPage />,
      },
      { path: "/projects/:name", element: <SingleProjectPage /> },
    ],
  },
  {
    path: "*",
    element: <p>404 Error - Nothing here...</p>,
  },
]);

export default router;
