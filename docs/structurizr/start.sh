#!/bin/bash

if ! command -v docker &> /dev/null; then
    echo "Erro: Docker não está instalado. Por favor, instale o Docker e tente novamente."
    exit 1
fi

CURRENT_USER_ID=$(id -u)
CURRENT_GROUP_ID=$(id -g)

# Executar o container como non-root
docker run -d --rm -p 9000:8080 \
    -v "$(pwd)/data:/usr/local/structurizr" \
    --user $CURRENT_USER_ID:$CURRENT_GROUP_ID \
    structurizr/lite