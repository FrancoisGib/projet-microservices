import { Divider } from "@mui/material";
import { ReactElement } from "react";
import NestedList from "../components/navbar/NestedList";
import NavBarItem from "../components/navbar/NavBarItem";
import { NavBarItemType } from "../interfaces/NavBarInterfaces";

export const convertItemsInComponent = (
  items: NavBarItemType[]
): ReactElement[] => {
  return items.map((item, index) => {
    let component;
    if ("children" in item) {
      component = <NestedList {...item} key={index} />;
    } else {
      component = <NavBarItem {...item} key={index} />;
    }
    return (
      <div className="navbar-item" key={index}>
        {" "}
        {component} <Divider />{" "}
      </div>
    );
  });
};
