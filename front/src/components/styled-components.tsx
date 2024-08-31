import styled from "@emotion/styled";

export const LowercasedTextExceptFirstLetter = styled.p`
  text-transform: lowercase;
  :first-letter {
    text-transform: uppercase;
  }
`;
