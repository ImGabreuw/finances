import { ConstraintsCode } from "./constraints-code";

export class ViolatedConstraint {
  readonly code: string;
  readonly message: string;

  constructor(code: string | ConstraintsCode, message: string) {
    this.code = typeof code === "string" ? code : (code as ConstraintsCode).valueOf();
    this.message = message;
  }
}
