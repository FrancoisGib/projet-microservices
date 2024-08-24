import CorporateFareIcon from "@mui/icons-material/CorporateFare";
import InboxIcon from "@mui/icons-material/MoveToInbox";
import PeopleIcon from "@mui/icons-material/People";
import RotateRightIcon from "@mui/icons-material/RotateRight";
import SettingsEthernetIcon from "@mui/icons-material/SettingsEthernet";
import { Divider, ListSubheader } from "@mui/material";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import { NavBarItemType } from "../../interfaces/NavBarInterfaces";
import { firstStringCharToUppercase } from "../../lib/functions";
import { convertItemsInComponent } from "../../lib/NavBarFunctions";
import { getUserOrganization } from "../../services/UserInformationService";
import KubernetesIcon from "./KubeIcon";

const items: NavBarItemType[] = [
  {
    title: "Organization",
    parentIcon: <CorporateFareIcon />,
    navigateTo: `/${getUserOrganization()}`,
    children: [{ title: "Users", icon: <PeopleIcon />, navigateTo: "/users" }],
  },
  {
    title: "Resources",
    parentIcon: <KubernetesIcon />,
    navigateTo: "/resources",
    children: [
      {
        title: "Services",
        icon: <SettingsEthernetIcon />,
        navigateTo: "/services",
      },
      {
        title: "Deployments",
        icon: <RotateRightIcon />,
        navigateTo: "/deployments",
      },
    ],
  },
  { title: "Projects", icon: <InboxIcon />, navigateTo: "/projects" },
];

export default function NavBar() {
  return (
    <div id="nav-bar-container">
      <Drawer
        variant="permanent"
        sx={{
          [`& .MuiDrawer-paper`]: {
            height: "100vh",
            width: "15vw",
            minWidth: "240px",
            position: "relative",
          },
        }}
      >
        <List>
          <ListSubheader
            component="div"
            id="nested-list-subheader"
            style={{ fontSize: "25px", textAlign: "center" }}
          >
            {firstStringCharToUppercase(getUserOrganization())}
          </ListSubheader>

          <Divider />

          {convertItemsInComponent(items)}
        </List>
      </Drawer>
    </div>
  );
}
