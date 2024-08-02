import { HTMLAttributes } from "react";

interface TableHeaderProps extends HTMLAttributes<HTMLTableRowElement> {
  children: React.ReactNode;
}

export function TableHeader({
  children,
  className,
  ...rest
}: TableHeaderProps) {
  return (
    <thead>
      <tr className={`m-0 border-t p-0 even:bg-muted ${className}`} {...rest}>
        {children}
      </tr>
    </thead>
  );
}
