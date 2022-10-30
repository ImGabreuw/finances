import { XPathService } from "../../domain/adapters/xpath-service.js";

class ReclameAquiXPathService extends XPathService {
  constructor() {
    super();
  }

  registerAll(): void {
    this.register({
      sectionName: "reputação",
      xPaths: [
        {
          elementName: "Nota do Reclame Aqui",
          xPath: `//*[@id="reputation"]/div[1]/div[1]/div[2]/span[2]/b`,
        },
        {
          elementName: "Nota do consumidor",
          xPath: `//*[@id="reputation"]/div[2]/div[1]/div[4]/span`,
        },
        {
          elementName: "Reclamações",
          xPath: `//*[@id="reputation"]/div[1]/div[2]/a[1]/div/div/b`,
        },
        {
          elementName: "Respondidas",
          xPath: `//*[@id="reputation"]/div[1]/div[2]/a[2]/div/div/b`,
        },
        {
          elementName: "Voltariam a fazer negócio",
          xPath: `//*[@id="reputation"]/div[2]/div[1]/div[2]/span`,
        },
        {
          elementName: "Índice de solução",
          xPath: `//*[@id="reputation"]/div[2]/div[1]/div[3]/span`,
        },
      ],
    });
  }
}

export { ReclameAquiXPathService };
