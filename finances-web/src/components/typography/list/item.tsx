import { HTMLAttributes } from "react";

interface ListItemProps extends HTMLAttributes<HTMLLIElement> {
  children: React.ReactNode;
}

export function ListItem({ children, className, ...rest }: ListItemProps) {
  return (
    <li className={`mt-2 ${className}`} {...rest}>
      {children}
    </li>
  );
}
