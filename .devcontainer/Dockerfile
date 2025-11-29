FROM ubuntu:26.04

WORKDIR /home/

COPY . .

RUN bash ./setup.sh

ENV PATH="/root/.cargo/bin:$PATH"