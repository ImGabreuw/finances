export type ConstraintsCodeName =
  | "invalid_type"
  | "unrecognized_keys"
  | "invalid_union"
  | "invalid_enum_value"
  | "invalid_arguments"
  | "invalid_return_type"
  | "invalid_date"
  | "invalid_string"
  | "too_small"
  | "too_big"
  | "not_multiple_of";

export enum ConstraintsCode {
  INVALID_TYPE = "invalid_type",
  UNRECOGNIZED_KEYS = "unrecognized_keys",
  INVALID_UNION = "invalid_union",
  INVALID_ENUM_VALUE = "invalid_enum_value",
  INVALID_ARGUMENTS = "invalid_arguments",
  INVALID_RETURN_TYPE = "invalid_return_type",
  INVALID_DATE = "invalid_date",
  INVALID_STRING = "invalid_string",
  TOO_SMALL = "too_small",
  TOO_BIG = "too_big",
  NOT_MULTIPLE_OF = "not_multiple_of",
}
