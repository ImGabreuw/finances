import { HttpStatus } from "../web/http-status";

export class BaseError extends Error {
  readonly httpStatus: HttpStatus;

  constructor(httpStatus: HttpStatus, message: string) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public getHttpStatusCode(): number {
    return this.httpStatus.valueOf();
  }
}
