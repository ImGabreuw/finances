import { ConstraintsCode } from "../../../src/shared/validation/constraints-code";
import { Field } from "../../../src/shared/validation/field";
import { ValidationError } from "../../../src/shared/validation/validation-error";
import { ViolatedConstraint } from "../../../src/shared/validation/violated-constraint";

test("should format validation error message", () => {
  const underTest = new ValidationError(
    new Field(
      "assetCode",
      " ",
      new ViolatedConstraint("invalid_string", "string inválida"),
      new ViolatedConstraint(
        ConstraintsCode.TOO_SMALL,
        "string deve conter pelo menos 1 caractere"
      )
    ),
    new Field(
      "title",
      " ",
      new ViolatedConstraint("invalid_string", "string inválida")
    )
  );

  expect(underTest.message).toBe(
    `[{"name":"assetCode","value":" ","violatedConstraints":[{"code":"invalid_string","message":"string inválida"},{"code":"too_small","message":"string deve conter pelo menos 1 caractere"}]},{"name":"title","value":" ","violatedConstraints":[{"code":"invalid_string","message":"string inválida"}]}]`
  );
});
