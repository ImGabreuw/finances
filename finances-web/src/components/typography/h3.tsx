import { HTMLAttributes, ReactNode } from "react";

interface H3Props extends HTMLAttributes<HTMLHeadingElement> {
  children: ReactNode;
}

export function H3({ children, className, ...rest }: H3Props) {
  return (
    <h3
      className={`scroll-m-20 text-2xl font-semibold tracking-tight ${className}`}
      {...rest}
    >
      {children}
    </h3>
  );
}
