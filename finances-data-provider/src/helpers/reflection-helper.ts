import { RegisterSearchSteps } from "../domain/adapters/steps-service";

class ReflectionHelper {
  private constructor() {}

  static isImplementsRegisterSearchSteps(
    object: any
  ): object is RegisterSearchSteps {
    return object.registerSearchSteps !== undefined;
  }
}

const STRIP_COMMENTS = /((\/\/.*$)|(\/\*[\s\S]*?\*\/))/mg;
const ARGUMENT_NAMES = /([^\s,]+)/g;

export function getParametersName(fn: Function): string[] {
  var fnStr = fn.toString().replace(STRIP_COMMENTS, '');
  var result = fnStr.slice(fnStr.indexOf('(')+1, fnStr.indexOf(')')).match(ARGUMENT_NAMES);

  return result === null ? [] : result;
}
