import { LucideLoaderCircle } from "lucide-react";

export default function Loading() {
  return (
    <div className="flex h-screen w-full flex-col items-center justify-center bg-background">
      <LucideLoaderCircle className="size-12 animate-spin" />
      <p className="text-lg font-medium text-foreground">Carregando...</p>
    </div>
  );
}
