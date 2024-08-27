import SearchIcon from "@mui/icons-material/Search";
import IconButton from "@mui/material/IconButton";
import InputBase from "@mui/material/InputBase";
import Paper from "@mui/material/Paper";
import { useRef } from "react";
import {
  QueryObserverResult,
  RefetchOptions,
  RefetchQueryFilters,
} from "react-query";
import Project from "../interfaces/Project";

type Callback = <TPageData>(
  options?: (RefetchOptions & RefetchQueryFilters<TPageData>) | undefined
) => Promise<QueryObserverResult<Project[], unknown>>;

export default function SearchBar({
  setSearchField,
  setFetchState,
  searchField,
}: {
  setSearchField: React.Dispatch<React.SetStateAction<string>>;
  setFetchState: React.Dispatch<React.SetStateAction<string>>;
  searchField: string;
}) {
  const inputRef = useRef<HTMLInputElement>(null);

  return (
    <Paper
      className="flex items-center"
      sx={{
        width: "50%",
      }}
    >
      <InputBase
        autoFocus={true}
        ref={inputRef}
        sx={{ ml: 1, flex: 1 }}
        placeholder="Search project"
        value={searchField}
        onChange={(event) => setSearchField(event.target.value)}
        onKeyDown={(e) => e.key === "Enter" && setFetchState(searchField)}
      />

      <IconButton
        type="button"
        sx={{ p: "10px" }}
        aria-label="search"
        onClick={() => setFetchState(searchField)}
      >
        <SearchIcon />
      </IconButton>
    </Paper>
  );
}
