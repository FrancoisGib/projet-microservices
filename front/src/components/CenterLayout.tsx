import { ReactNode } from "react";

export default function CenterLayout({ children }: { children: ReactNode }) {
  return (
    <div
      id="center-layout"
      className="flex justify-center items-center h-screen w-screen"
    >
      {children}
    </div>
  );
}
