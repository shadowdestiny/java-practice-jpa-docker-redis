FROM postgres:9.6 as base

ENV PG_FULL 9.6

RUN apt-get update -qq \
    && apt-get install -yq --no-install-recommends \
    ca-certificates \
    libpq-dev \
    postgresql-server-dev-all \
    postgresql-common \
    wget \
    git \
    unzip \
    make \
    build-essential \
    libssl-dev \
    libkrb5-dev
