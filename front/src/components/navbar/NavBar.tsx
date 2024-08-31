import CorporateFareIcon from "@mui/icons-material/CorporateFare";
import InboxIcon from "@mui/icons-material/MoveToInbox";
import PeopleIcon from "@mui/icons-material/People";
import RotateRightIcon from "@mui/icons-material/RotateRight";
import SettingsEthernetIcon from "@mui/icons-material/SettingsEthernet";
import { Divider, ListSubheader } from "@mui/material";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import { NavBarItemType } from "../../interfaces/NavBarInterfaces";
import { convertItemsInComponent } from "../../lib/NavBarFunctions";
import userInformationService from "../../services/userInformationService";
import { LowercasedTextExceptFirstLetter } from "../styled-components";
import KubernetesIcon from "./KubeIcon";

const items: NavBarItemType[] = [
  {
    title: "Organization",
    parentIcon: <CorporateFareIcon />,
    navigateTo: `/${userInformationService
      .getUserPrincipal()
      ?.organizationName?.toLowerCase()}`,
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

const organizationName =
  userInformationService.getUserPrincipal()?.organizationName ?? " ";

export default function NavBar() {
  return (
    <div id="nav-bar-container">
      <Drawer
        variant="permanent"
        sx={{
          [`& .MuiDrawer-paper`]: {
            height: "100vh",
            width: "14vw",
            minWidth: "240px",
            position: "relative",
          },
        }}
      >
        <List sx={{ paddingTop: "4px" }}>
          <ListSubheader
            component="div"
            id="nested-list-subheader"
            style={{
              fontSize: "25px",
              textAlign: "center",
              backgroundColor: "#0078d4",
              color: "white",
              fontWeight: "light",
              fontFamily:
                "az_ea_font, Segoe UI, az_font, system-ui, -apple-system, BlinkMacSystemFont, Roboto, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif",
            }}
          >
            <LowercasedTextExceptFirstLetter>
              {organizationName}
            </LowercasedTextExceptFirstLetter>
          </ListSubheader>
          <Divider />
          {convertItemsInComponent(items)}
        </List>
      </Drawer>
    </div>
  );
}
