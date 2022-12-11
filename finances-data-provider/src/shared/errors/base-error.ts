import { HttpStatus } from "../web/http-status";

export class BaseError extends Error {
  private readonly _httpStatus: HttpStatus;

  constructor(httpStatus: HttpStatus, message: string) {
    super(message);
    this._httpStatus = httpStatus;
  }

  public get httpStatus(): HttpStatus {
    return this._httpStatus;
  }

  public getHttpStatusCode(): number {
    return this._httpStatus.valueOf();
  }

}
