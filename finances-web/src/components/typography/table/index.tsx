import { HTMLAttributes } from "react";

interface TableProps extends HTMLAttributes<HTMLTableElement> {
  children: React.ReactNode;
}

export function Table({ children, className, ...rest }: TableProps) {
  return (
    <div className="my-6 w-full overflow-y-auto">
      <table className={`w-full ${className}`} {...rest}>
        {children}
      </table>
    </div>
  );
}
