import VisibilityIcon from "@mui/icons-material/Visibility";
import VisibilityIconOff from "@mui/icons-material/VisibilityOff";
import Button from "@mui/material/Button";
import { useEffect, useState } from "react";
import CenterLayout from "../components/CenterLayout";
import ButtonWithFocus from "../components/forms/ButtonWithFocus";
import { useFormValidator } from "../hooks/useFormValidator";
import { isAuthenticated } from "../lib/Auth";
import { Validator } from "../lib/form-validator";
import router from "../routes/Router";
import "../styles/LoginPage.css";
import authService from "../services/authService";

export default function LoginPage() {
  const { form, setForm, isValid, setFocus } = useFormValidator(
    {
      name: "username",
      initialValue: "",
      validationFunctions: [Validator.maxLength(25), Validator.minLength(5)],
    },
    {
      name: "password",
      initialValue: "",
      validationFunctions: [Validator.password],
    }
  );

  const [showPassword, setShowPassword] = useState(false);

  const [loginRequestError, setLoginRequestError] = useState("");

  const handleSubmit = async () => {
    if (isValid) {
      const response = await authService
        .login({
          username: form["username"].value,
          password: form["password"].value,
        })
        .catch((error) => {
          setLoginRequestError("Failed to authenticate");
          console.error(error);
        });
      console.log(response);
    }
    console.log("ok");
  };

  useEffect(() => {
    if (isAuthenticated()) {
      router.navigate("/");
    }
  }, []);

  return (
    <CenterLayout>
      <form
        id="login-form"
        style={{ backgroundColor: loginRequestError ?? "red" }}
      >
        <h2 id="login-form-title">Login</h2>
        <div id="login-fields-container">
          <ButtonWithFocus
            {...form["username"]}
            setFocus={setFocus}
            setInput={setForm}
            label={"Username"}
            inputName={"username"}
            type="text"
            className="login-form-input"
          />

          <div id="password-container">
            <ButtonWithFocus
              {...form["password"]}
              setFocus={setFocus}
              setInput={setForm}
              label="Password"
              inputName="password"
              type={showPassword ? "text" : "password"}
              className="login-form-input"
            />
            <div
              id="password-visibility-icon"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? <VisibilityIcon /> : <VisibilityIconOff />}
            </div>
          </div>
        </div>
        <div id="form-buttons-container">
          <Button
            variant="contained"
            className="form-button"
            type="button"
            onClick={handleSubmit}
          >
            Se connecter
          </Button>
          <Button variant="outlined" className="form-button">
            Créer un compte
          </Button>
        </div>
      </form>
      <p>{loginRequestError}</p>
    </CenterLayout>
  );
}
