import { HTMLAttributes } from "react";

type Align = "left" | "center" | "right";

interface TableColumnProps extends HTMLAttributes<HTMLTableCellElement> {
  align?: Align;
  children: React.ReactNode;
}

export function TableColumn({
  children,
  align = "left",
  className,
  ...rest
}: TableColumnProps) {
  const alignmentClasses = {
    left: "text-left",
    center: "text-center",
    right: "text-right",
  };

  return (
    <th
      className={`border px-4 py-2 font-bold ${alignmentClasses[align]} ${className}`}
      {...rest}
    >
      {children}
    </th>
  );
}
