import moment from "moment";

export function parse(date: string, format: string): Date {
  return moment(date, format).toDate();
}

export function parseLocalFormat(date: string): Date {
  return moment(date, "DD/MM/YYYY").toDate();
}
