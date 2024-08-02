import { HTMLAttributes } from "react";

interface TableBodyProps extends HTMLAttributes<HTMLTableSectionElement> {
  children: React.ReactNode;
}

export function TableBody({ children, ...rest }: TableBodyProps) {
  return <tbody {...rest}>{children}</tbody>;
}
