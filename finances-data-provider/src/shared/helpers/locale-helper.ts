class LocaleHelper {
  static getUserLocale(): string {
    const languagePreferences = navigator.languages;

    if (!languagePreferences || languagePreferences.length === 0) {
      return "pt-br";
    }

    return languagePreferences[0];
  }
}

export { LocaleHelper };
