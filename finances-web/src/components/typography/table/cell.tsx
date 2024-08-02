import { HTMLAttributes } from "react";

type Align = "left" | "center" | "right";

interface TableCellProps extends HTMLAttributes<HTMLTableCellElement> {
  align?: Align;
  children: React.ReactNode;
}

export function TableCell({
  children,
  align = "left",
  className,
  ...rest
}: TableCellProps) {
  const alignmentClasses = {
    left: "text-left",
    center: "text-center",
    right: "text-right",
  };

  return (
    <td
      className={`border px-4 py-2 ${alignmentClasses[align]} ${className}`}
      {...rest}
    >
      {children}
    </td>
  );
}
