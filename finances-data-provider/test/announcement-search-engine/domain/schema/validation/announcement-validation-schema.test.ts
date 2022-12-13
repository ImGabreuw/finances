import { Announcement } from "../../../../../src/announcement-search-engine/domain/announcement";
import { ValidationError } from "../../../../../src/shared/validation/validation-error";

test("should pass validation", () => {
  const underTest = new Announcement(
    "AGRO3",
    "Fato relevante",
    new Date(Date.parse("2022-12-12"))
  );

  expect(underTest).not.toBeNull();
});

test("should throw ValidationError when create Announcement because assetCode is blank", () => {
  expect(
    () =>
      new Announcement(
        " ",
        "Fato relevante",
        new Date(Date.parse("2022-12-12"))
      )
  ).toThrow(
    new ValidationError({
      name: "assetCode",
      value: " ",
      violatedConstraints: [
        {
          code: "too_small",
          message: "String must contain at least 1 character(s)",
        },
      ],
    })
  );
});
