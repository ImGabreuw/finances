import { HTMLAttributes, ReactNode } from "react";

interface LeadProps extends HTMLAttributes<HTMLParagraphElement> {
  children: ReactNode;
}

export function Lead({ children, className, ...rest }: LeadProps) {
  return (
    <p
      className={`text-xl text-muted-foreground ${className}`}
      {...rest}
    >
      {children}
    </p>
  );
}
