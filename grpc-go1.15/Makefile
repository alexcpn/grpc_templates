CURRENTDIR =  $(shell pwd)
PARENTDIR = $(shell dirname $(CURRENTDIR))
GO111MODULE=auto
VALIDATE_PROTO=v0.4.1
GOLANG-CLI := $(shell $(shell go env GOPATH)/bin/golangci-lint --version 2>/dev/null)

all: clean  build_proto_with_validate build_go 


clean:
	-rm -rf generated/grpc-go/* out/
	-rm -f test_server/test_server
	-rm -f report.xml server-lint.xml unittest-result.xml cover.out cover.html

build_proto_with_validate:
	protoc   -I ./internal_interfaces/   \
	 --go_out=plugins=grpc:./generated   \
	 ./internal_interfaces/grpc-go/*.proto

build_go:
ifdef GOLANG-CLI
	@echo "Found version $(GOLANG-CLI)"
else
	@echo GOLANG-CLI Not found - installing
	curl -sSfL https://raw.githubusercontent.com/golangci/golangci-lint/master/install.sh \
	 		| sh -s -- -b $(shell go env GOPATH)/bin v1.32.2
endif
	
	
	#go get github.com/golangci/golangci-lint/cmd/golangci-lint@v1.32.2
	go get github.com/jstemmer/go-junit-report

	# let us do the test, get the coverage
	cd integration_test && go test -coverprofile=../cover.out -coverpkg=server -v | go-junit-report > ../unittest-result.xml
	cd integration_test && go tool cover -html=../cover.out -o ../cover.html

	# lets run the linter with continue on fail -notice the '-' in the begining of next line
	-cd server && golangci-lint run --out-format checkstyle  > ../server-lint.xml
	#-cd test_server && golangci-lint run --out-format checkstyle  > test_server.xml
	#-cd test_client && golangci-lint run --out-format checkstyle  > test_client.xml
	cd test_server && go build 
	cd test_client && go build 

# Multi-stage docker
# note that in the Dockerfile make all is called !! so it makes in docker
in-docker:
	DOCKER_BUILDKIT=1 docker build --build-arg  http_proxy=${HTTP_PROXY} \
	 --build-arg https_proxy=${HTTP_PROXY} -f  Dockerfile --target=testresult --output out  $(CURRENTDIR) 
	DOCKER_BUILDKIT=1 docker build --build-arg  http_proxy=${HTTP_PROXY} \
	 --build-arg https_proxy=${HTTP_PROXY}  -t grpc-go:3.0 -f  Dockerfile  $(CURRENTDIR) 

	 echo " ---- Built grpc-go Docker Image -- grpc-go -----------"

