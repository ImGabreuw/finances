import { BaseError } from "../../../shared/errors/base-error";
import { HttpStatus } from "../../../shared/web/http-status";

export class AssetNotFoundError extends BaseError {
  constructor(assetCode: string) {
    super(
      HttpStatus.BAD_REQUEST,
      `Não foi possível encontrar um ativo com o código [${assetCode}].`
    );
  }
}
