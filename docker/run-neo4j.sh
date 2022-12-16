#!/bin/bash

docker run -it --rm \
  --publish=7474:7474 --publish=7687:7687 \
  -v data:/data -v conf:/var/lib/neo4j/conf -v plugins:/var/lib/neo4j/plugins -v logs:/logs \
  -e NEO4J_AUTH=neo4j/12345678 \
  --env NEO4J_PLUGINS='["graph-data-science"]' \
  neo4j:5.2