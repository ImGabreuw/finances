import { HTMLAttributes, ReactNode } from "react";

interface MutedProps extends HTMLAttributes<HTMLParagraphElement> {
  children: ReactNode;
}

export function Muted({ children, className, ...rest }: MutedProps) {
  return (
    <p
      className={`text-sm text-muted-foreground ${className}`}
      {...rest}
    >
      {children}
    </p>
  );
}
