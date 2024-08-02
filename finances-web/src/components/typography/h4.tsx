import { HTMLAttributes, ReactNode } from "react";

interface H4Props extends HTMLAttributes<HTMLHeadingElement> {
  children: ReactNode;
}

export function H4({ children, className, ...rest }: H4Props) {
  return (
    <h4
      className={`scroll-m-20 text-xl font-semibold tracking-tight ${className}`}
      {...rest}
    >
      {children}
    </h4>
  );
}
