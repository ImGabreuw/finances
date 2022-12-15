export function format(message: string, ...values: any[]) {
  for (let index = 0; index < values.length; index++) {
    message = message.replace(`{${index}}`, values[index]);
  }

  return message;
}
