import { ReactNode } from "react";
import NavBar from "./NavBar";
import "../../styles/NavBarLayout.css";

export default function NavBarLayout({ children }: { children: ReactNode }) {
  return (
    <div id="nav-bar-layout">
      <NavBar />
      {children}
    </div>
  );
}
