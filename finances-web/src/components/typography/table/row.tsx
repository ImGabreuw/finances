import { HTMLAttributes } from "react";

interface TableRowProps extends HTMLAttributes<HTMLTableRowElement> {
  children: React.ReactNode;
}

export function TableRow({ children, className, ...rest }: TableRowProps) {
  return (
    <tr className={`m-0 border-t p-0 even:bg-muted ${className}`} {...rest}>
      {children}
    </tr>
  );
}
