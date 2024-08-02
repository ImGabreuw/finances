"use client";

import { HTMLAttributes } from "react";
import { Button } from "./ui/button";
import { useToast } from "./ui/use-toast";
import { LucideCopy } from "lucide-react";

interface CopyButtonProps extends HTMLAttributes<HTMLButtonElement> {
  children?: string;
  contentToCopy: string;
}

export function CopyButton({
  children,
  contentToCopy,
  className,
  ...rest
}: CopyButtonProps) {
  const { toast } = useToast();

  const copyToClipboard = async () => {
    try {
      await navigator.clipboard.writeText(contentToCopy);
      toast({
        title: "Texto copiado para a área de transferência!",
      });
    } catch (error) {
      toast({
        title: "Falha ao copiar o texto.",
        variant: "destructive",
      });
    }
  };
  return (
    <Button
      onClick={copyToClipboard}
      variant={"ghost"}
      size={children ? "default" : "icon"}
      className={`gap-2 ${className}`}
      {...rest}
    >
      <LucideCopy />
      {children}
    </Button>
  );
}
