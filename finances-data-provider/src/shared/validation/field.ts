import { ViolatedConstraint } from "./violated-constraint";

export class Field {
  readonly name: string;
  readonly value: any;
  readonly violatedConstraints: ViolatedConstraint[];


  constructor(name: string, value: any, ...violatedConstraints: ViolatedConstraint[]) {
    this.name = name;
    this.value = value;
    this.violatedConstraints = violatedConstraints;
  }
}
