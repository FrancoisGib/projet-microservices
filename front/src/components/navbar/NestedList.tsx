import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";
import Collapse from "@mui/material/Collapse";
import List from "@mui/material/List";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import { ReactElement, useState } from "react";
import { ListNavBarItem } from "../../interfaces/NavBarInterfaces";
import NavBarItem from "./NavBarItem";

export default function NestedList({
  title,
  navigateTo,
  parentIcon,
  children,
}: {
  title: string;
  navigateTo: string;
  parentIcon: ReactElement;
  children: ListNavBarItem[];
}) {
  const [open, setOpen] = useState(false);

  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <>
      <ListItemButton onClick={handleClick}>
        <ListItemIcon>{parentIcon}</ListItemIcon>

        <ListItemText primary={title} />
        {open ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>

      <Collapse in={open} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          {children.map((child, index) => (
            <NavBarItem
              {...child}
              padding={true}
              navigateTo={navigateTo + child.navigateTo}
              key={index}
            />
          ))}
        </List>
      </Collapse>
    </>
  );
}
