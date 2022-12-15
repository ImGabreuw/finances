class CurrencyHelper {
  static format(
    value: number | null,
    locale: string = "pt-br",
    currency: string = "BRL"
  ): string {
    if (value === null) {
      value = 0;
    }

    return value.toLocaleString(locale, {
      style: "currency",
      currency,
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    });
  }

  static removeBrazilianCurrencySymbol(text: string): string {
    text = text.toUpperCase().trim();

    if (!text.startsWith("R$")) return text;

    return text.substring(2);
  }
}

export { CurrencyHelper };
