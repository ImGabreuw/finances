import { SectionXPaths } from "../types/xpath-type.js";

abstract class XPathService {
  private readonly XPATH = new Map<string, SectionXPaths>;

  protected constructor() {
    this.registerAll();
  }

  getAll(): SectionXPaths[] {
    return Array.from(this.XPATH.values());
  }

  getBySectionName(sectionName: string): SectionXPaths {
    if (!this.hasXPath(sectionName)) {
      throw new Error(`não existe seção de XPath com o nome "${sectionName}"`);
    }

    return this.XPATH.get(sectionName) as SectionXPaths;
  }

  hasXPath(sectionName: string): boolean {
    return this.XPATH.has(sectionName);
  }

  register(sectionXPaths: SectionXPaths): void {
    const sectionName = sectionXPaths.sectionName;

    if (this.hasXPath(sectionName)) {
      throw new Error(
        `uma seção de XPath já foi registrada com o nome "${sectionName}"`
      );
    }

    this.XPATH.set(sectionName, sectionXPaths);
  }

  abstract registerAll(): void;

}

export { XPathService };
