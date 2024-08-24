import { ReactNode } from "react";

export default function CenterLayout({ children }: { children: ReactNode }) {
  return (
    <div
      id="center-layout"
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      {children}
    </div>
  );
}
