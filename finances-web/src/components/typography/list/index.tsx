import { HTMLAttributes } from "react";

interface ListProps extends HTMLAttributes<HTMLUListElement> {
  children: React.ReactNode;
}

export function List({ children, className, ...rest }: ListProps) {
  return (
    <ul className={`my-6 ml-6 list-disc ${className}`} {...rest}>
      {children}
    </ul>
  );
}
