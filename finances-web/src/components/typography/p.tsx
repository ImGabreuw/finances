import { HTMLAttributes, ReactNode } from "react";

interface PProps extends HTMLAttributes<HTMLParagraphElement> {
  children: ReactNode;
}

export function P({ children, className, ...rest }: PProps) {
  return (
    <p
      className={`leading-7 [&:not(:first-child)]:mt-6 ${className}`}
      {...rest}
    >
      {children}
    </p>
  );
}
