#!/bin/bash

container_id=$(docker ps -q --filter "ancestor=structurizr/lite")

if [ -z "$container_id" ]; then
    echo "Nenhum container com a imagem 'structurizr/lite' está em execução."
else
    docker stop "$container_id"
    echo "Container com a imagem 'structurizr/lite' foi encerrado."
fi