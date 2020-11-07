# Minimal Go GRPC Service - Dockerized

GO 1.5, GRPC v1.32.0, GO PROTOBUFFER v1.4.3

## Instructions

- Make Base Image and push to registry

   `make -f Makefile.baseimage` 
   
   This creates the Go Docker Image with GRPC - `grpc/go:1.15`

- Build locally

    `make all`

   You need to have Go and Protoc compiler installed in you machine for this

- Build in Docker

   `make  in-docker`

   Builds the image in `grpc/go:1.15` and copies the build results like junit report, coverage report etc to a folder named `out` in the current directory. For this Buildkit features are used

