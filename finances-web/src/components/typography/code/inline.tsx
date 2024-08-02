import { HTMLAttributes, ReactNode } from "react";

interface InlineCodeProps extends HTMLAttributes<HTMLElement> {
  children: ReactNode;
}

export function InlineCode({ children, className, ...rest }: InlineCodeProps) {
  return (
    <code
      className={`relative rounded bg-muted px-[0.3rem] py-[0.2rem] font-mono text-sm font-semibold ${className}`}
      {...rest}
    >
      {children}
    </code>
  );
}
