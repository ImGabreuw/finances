export function mapPathToProperty(path: (string | number)[]): string {
  let property = "";

  for (let i = 0; i < path.length; i++) {
    if (typeof path[i] === "number") {
      property += `[${path[i]}]`;
      continue;
    }

    property += `${path[i]}`;

    if (i < path.length && typeof path[i + 1] === "string") {
      property += ".";
    }
  }

  return property;
}
