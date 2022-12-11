import { getFirstKeyFrom } from "../../helpers/enum-helper";
import { format } from "../../helpers/string-helper";
import { HttpStatus } from "../web/http-status";
import { BaseError } from "./base-error";

export class ValidationError extends BaseError {
  constructor(validationType: ValidationType, property: string, value: any) {
    super(
      HttpStatus.BAD_REQUEST,
      format(
        validationType.valueOf(),
        getFirstKeyFrom(ValidationType, validationType),
        property,
        value
      )
    );
  }
}

export enum ValidationType {
  NOT_BLANK = `Violação da constraint [{0}] da propriedade [{1}] com valor [{2}]: não pode estar em branco`,
  NOT_NULL = "Violação da constraint [{0}] da propriedade [{1}] com valor [{2}]: não pode ser nulo",
}
