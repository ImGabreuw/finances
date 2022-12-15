import { HttpStatus } from '../web/http-status';
import { BaseError } from './base-error';

export class RequiredUseCaseInputError extends BaseError {
  constructor(inputPropsName: string) {
    super(HttpStatus.BAD_REQUEST, `[${inputPropsName}] is required.`)
  }
}
