import { ReactElement } from "react";

export interface ListNavBarItem {
  title: string;
  icon: ReactElement;
  navigateTo: string;
}

export interface NestedListItem {
  title: string;
  parentIcon: ReactElement;
  children: ListNavBarItem[];
  navigateTo: string;
  padding?: boolean;
}

export type NavBarItemType = NestedListItem | ListNavBarItem;
