import { HTMLAttributes, ReactNode } from "react";

interface H1Props extends HTMLAttributes<HTMLHeadingElement> {
  children: ReactNode;
}

export function H1({ children, className, ...rest }: H1Props) {
  return (
    <h1
      className={`scroll-m-20 text-4xl font-extrabold tracking-tight lg:text-5xl ${className}`}
      {...rest}
    >
      {children}
    </h1>
  );
}
