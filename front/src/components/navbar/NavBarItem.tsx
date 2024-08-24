import {
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import { ReactElement } from "react";
import { Link } from "react-router-dom";

export default function NavBarItem({
  title,
  navigateTo,
  icon,
  padding = false,
}: {
  title: string;
  navigateTo: string;
  icon: ReactElement;
  padding?: boolean;
}) {
  return (
    <Link to={navigateTo}>
      <ListItem key={title} disablePadding>
        <ListItemButton sx={{ pl: padding ? 4 : 2 }}>
          <ListItemIcon>{icon}</ListItemIcon>
          <ListItemText primary={title} />
        </ListItemButton>
      </ListItem>
    </Link>
  );
}
