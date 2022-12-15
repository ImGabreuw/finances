export function getFirstKeyFrom(enumClass: any, value: any): string {
  const keys = Object.keys(enumClass).filter((key) => enumClass[key] == value);

  return keys.length == 0 ? "" : keys[0];
}
