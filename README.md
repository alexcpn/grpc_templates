# GRPC Templates

## Bare minimum GRPC templates in Go, C++ and TypeScript with Anchore Scan


GRPC is cool; effecient and a building block for microservices. It follows strongly typed and versioned interface with very effecient implementation (HTTP2) in all popular languages. 

Unlike REST it is slightly complex and hence there is a lot of reluctance by many enterprise programmers to take it up. However using this as a building block for micro-services over REST could mean the difference between a messy system with interface incompatibility, to a smooth evolving system.

More of the advantages here https://medium.com/better-software/rest-in-peace-grpc-for-micro-service-and-grpc-for-the-web-a-how-to-908cc05e1083

The official docs are the first and best place to start

- https://grpc.io/docs/quickstart/
- https://grpc.io/docs/languages/java/basics/

However the samples in these are not very clear; and also complex as they show a lot of features like streaming. Also there is no bare Docker file in these repos.

This repo is meant to be a good starter - and for production ready base images.
