import { NotificationStatus } from "./enums/notification-status";
import { validate } from "./schema/validation/announcement-validation-schema";

export class Announcement {
  private _notificationStatus: NotificationStatus;

  constructor(
    readonly assetCode: string,
    readonly title: string,
    readonly releaseDate: Date,
    readonly downloadUrl: string,
    notificationStatus?: NotificationStatus
  ) {
    this._notificationStatus = notificationStatus || NotificationStatus.PENDING;

    validate(this);
  }

  public get notificationStatus(): NotificationStatus {
    return this._notificationStatus;
  }

  public notified(): void {
    this._notificationStatus = NotificationStatus.NOTIFIED;
  }

  public error(): void {
    this._notificationStatus = NotificationStatus.ERROR;
  }
}
