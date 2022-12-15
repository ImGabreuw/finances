import { mapPathToProperty } from "../../src/shared/helpers/zod-helper";

test("should map zod path to property of array", () => {
  const zodPath = ["names", 1];

  const underTest = mapPathToProperty(zodPath);

  expect(underTest).toBe("names[1]");
});

test("should map zod path to nested properties", () => {
  const zodPath = ["address", "zipCode"];

  const underTest = mapPathToProperty(zodPath);

  expect(underTest).toBe("address.zipCode");
});

test("should map zod path to nested properties with array", () => {
  const zodPath = ["address", "names", 2];

  const underTest = mapPathToProperty(zodPath);

  expect(underTest).toBe("address.names[2]");
});
