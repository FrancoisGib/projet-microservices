import { TextField } from "@mui/material";
import {
  ChangeEventFunction,
  FocusEventFunction,
} from "../../lib/form-validator";

export default function ButtonWithFocus({
  className,
  type,
  inputName,
  label,
  isFocused,
  isValid,
  value,
  setInput,
  setFocus,
}: {
  className?: string;
  type: string;
  inputName: string;
  label: string;
  isValid: boolean;
  isFocused: boolean;
  value: string;
  setInput: ChangeEventFunction;
  setFocus: FocusEventFunction;
}) {
  return (
    <TextField
      className={className}
      type={type}
      name={inputName}
      label={label}
      value={value}
      onChange={setInput}
      onBlur={setFocus}
      error={!isValid && !isFocused && value.length > 0}
    />
  );
}
