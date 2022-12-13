import { BaseError } from "../errors/base-error";
import { HttpStatus } from "../web/http-status";
import { Field } from "./field";

export class ValidationError extends BaseError {
  readonly fields: Field[];

  constructor(...fields: Field[]) {
    super(HttpStatus.BAD_REQUEST, JSON.stringify(fields));
    this.fields = fields;
  }

  public prettyFormat(): string {
    return JSON.stringify(this, null, '\t');
  }

}
