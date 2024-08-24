const getItem = (key: string): string | null => localStorage.getItem(key);
const setItem = (key: string, value: string): void =>
  localStorage.setItem(key, value);

export const getUserOrganization = () => getItem("organization") as string;
export const setUserOrganization = (value: string) =>
  setItem("organization", value);

export const getUserName = () => getItem("name");
export const setUserName = (value: string) => setItem("name", value);

export const getUserId = () => getItem("id");
export const setUserId = (value: string) => setItem("id", value);
