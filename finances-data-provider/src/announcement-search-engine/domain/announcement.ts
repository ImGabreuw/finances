import { validate } from "./schema/validation/announcement-validation-schema";

export class Announcement {
  constructor(
    readonly assetCode: string,
    readonly title: string,
    readonly releaseDate: Date,
    readonly downloadUrl: string
  ) {
    validate(this);
  }
}
