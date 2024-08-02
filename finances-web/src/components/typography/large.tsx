import { HTMLAttributes, ReactNode } from "react";

interface LargeProps extends HTMLAttributes<HTMLDivElement> {
  children: ReactNode;
}

export function Large({ children, className, ...rest }: LargeProps) {
  return (
    <div className={`text-lg font-semibold ${className}`} {...rest}>
      {children}
    </div>
  );
}
