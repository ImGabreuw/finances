import { Action } from "./action-type";

export type SectionSteps = {
  sectionName: string,
  steps: Step[],
}

export type Step = {
  elementName: string;
  action: Action;
  text?: string | null;
  selector: string;
};
