#!/usr/bin/env bash

CURRENT="$(
  cd "$(dirname "$0")"
  pwd
)"

# Create network
#docker network inspect fae_backend &>/dev/null || docker network create fae_backend

# Start service
docker-compose -p fae-team-3-service \
  -f "$CURRENT/src/main/docker/docker-compose.yml" \
  -f "$CURRENT/src/main/docker/docker-compose.dev.yml" \
  up -d
