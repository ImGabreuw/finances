import { HTMLAttributes, ReactNode } from "react";

interface SmallProps extends HTMLAttributes<HTMLParagraphElement> {
  children: ReactNode;
}

export function Small({ children, className, ...rest }: SmallProps) {
  return (
    <small
      className={`text-sm font-medium leading-none ${className}`}
      {...rest}
    >
      {children}
    </small>
  );
}
