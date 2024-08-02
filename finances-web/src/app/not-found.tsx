import Link from "next/link";

import { H1 } from "@/components/typography/h1";
import { Lead } from "@/components/typography/lead";
import { P } from "@/components/typography/p";
import { Button } from "@/components/ui/button";

export default function NotFound() {
  return (
    <div className="container flex h-screen flex-col items-center justify-center gap-4 bg-background">
      <Lead className="text-9xl">404</Lead>
      <H1 className="text-center">Oops! Página não encontrada.</H1>
      <P className="text-center">
        A página que você procura não existe ou foi movida.
      </P>
      <Button>
        <Link href="/" prefetch={false}>
          Voltar para o início
        </Link>
      </Button>
    </div>
  );
}
