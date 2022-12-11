import { getFirstKeyFrom } from "../../../src/helpers/enum-helper";
import {
  ValidationError,
  ValidationType,
} from "../../../src/shared/errors/validation-error";

test("should format validation error message", () => {
  const validationType = ValidationType.NOT_BLANK;
  const property = "assetCode";
  const value = " ";

  const underTest = new ValidationError(validationType, property, value);

  expect(underTest.message).toBe(
    `Violação da constraint [NOT_BLANK] da propriedade [assetCode] com valor [ ]: não pode estar em branco`
  );
});
