# Dockerfile to build an image that contains dependencies to build your serice
### -------------To make your MS build faster in make-in-docker-----------------
FROM grpc/go:1.15 as builder

WORKDIR /grpc-go

COPY . /grpc-go
RUN make all



### Build a slim GRPC Go Container
# final stage
FROM alpine:3.12.1 as final
WORKDIR /app

# Copy the generated build binaries from previous step

COPY  --from=builder /grpc-go/test_server/test_server /app/

# https://stackoverflow.com/questions/34729748/installed-go-binary-not-found-in-path-on-alpine-linux-docker
RUN mkdir /lib64 && ln -s /lib/libc.musl-x86_64.so.1 /lib64/ld-linux-x86-64.so.2
ENTRYPOINT ["./test_server"]

FROM scratch as testresult
COPY --from=builder /grpc-go/unittest-result.xml .
COPY --from=builder /grpc-go/cover.out .
COPY --from=builder /grpc-go/server-lint.xml .
