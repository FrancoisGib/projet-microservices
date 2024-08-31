import { Button, ButtonGroup, Divider, Menu, MenuItem } from "@mui/material";
import { LowercasedTextExceptFirstLetter } from "../styled-components";
import { useState } from "react";

interface KubeResource {
  name: string;
}

interface Services extends KubeResource {
  type: string;
  ports: {
    nodePort?: number;
    port: number;
    targetPort: number;
  }[];
}

interface Deployments extends KubeResource {
  uid: string;
  name: string;
  namespace: string;
  creationTimestamp: string;
  labels: Record<string, string>;
  spec: DeploymentSpec;
}

interface DeploymentSpec {
  replicas: number;
  selector: {
    matchLabels: Record<string, string>;
  };
  template: {};
}

interface PodSpec {
  containers: Container[];
}

interface Container {
  name: string;
  env: ContainerEnv[];
  image: string;
  ports: ContainerPort[];
}

interface ContainerEnv {
  name: string;
  value: string;
}

interface ContainerPort {
  containerPort: number;
}

export default function ResourceCard({}) {
  return (
    <div
      style={{ boxShadow: "var(--card-box-shadow)" }}
      className="project-card w-[15%] min-w-[250px] h-[20vh] rounded-lg "
    >
      <LowercasedTextExceptFirstLetter>salut</LowercasedTextExceptFirstLetter>
    </div>
  );
}

export function CustomizedMenus() {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <div id="resources-menu-container" className="w-[100%]">
      <div className="border-x border-gray py-2 w-fit">
        <Button
          id="demo-customized-button"
          aria-controls={open ? "demo-customized-menu" : undefined}
          aria-haspopup="true"
          aria-expanded={open ? "true" : undefined}
          variant="text"
          disableElevation
          onClick={handleClick}
        >
          Create resource
        </Button>
        <Menu
          id="demo-customized-menu"
          MenuListProps={{
            "aria-labelledby": "demo-customized-button",
          }}
          anchorEl={anchorEl}
          open={open}
          onClose={handleClose}
        >
          <MenuItem onClick={handleClose} disableRipple>
            Deployment
          </MenuItem>
          <MenuItem onClick={handleClose} disableRipple>
            Service
          </MenuItem>
        </Menu>
      </div>
      <Divider />
    </div>
  );
}
