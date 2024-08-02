import { HTMLAttributes, ReactNode } from "react";

interface BlockquoteProps extends HTMLAttributes<HTMLQuoteElement> {
  children: ReactNode;
}

export function Blockquote({ children, className, ...rest }: BlockquoteProps) {
  return (
    <blockquote
      className={`mt-6 border-l-2 pl-6 italic ${className}`}
      {...rest}
    >
      {children}
    </blockquote>
  );
}
