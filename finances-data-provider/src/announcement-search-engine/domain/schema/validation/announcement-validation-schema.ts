import { z } from "zod";
import { mapPathToProperty } from "../../../../helpers/zod-helper";
import { Field } from "../../../../shared/validation/field";
import { ValidationError } from "../../../../shared/validation/validation-error";
import { Announcement } from "../../announcement";
import { NotificationStatus } from "../../enums/notification-status";

const announcementSchema = z.object({
  assetCode: z.string().trim().min(1),
  title: z.string().trim().min(1),
  releaseDate: z.date().max(new Date()),
  notificationStatus: z.nativeEnum(NotificationStatus),
});

export function validate(announcement: Announcement): Announcement {
  const result = announcementSchema.safeParse(announcement);

  if (!result.success) {
    const fields = new Map<string, Field>();

    result.error.issues.forEach(({ code, path, message }) => {
      const propertyName = mapPathToProperty(path);

      if (fields.has(propertyName)) {
        const existingField = fields.get(propertyName);

        existingField?.violatedConstraints.push({ code, message });
      }

      fields.set(propertyName, {
        name: propertyName,
        value: eval(`announcement.${propertyName}`),
        violatedConstraints: [
          {
            code,
            message,
          },
        ],
      });
    });

    throw new ValidationError(...fields.values());
  }

  return announcement;
}
