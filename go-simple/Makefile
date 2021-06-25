CURRENTDIR =  $(shell pwd)
PARENTDIR = $(shell dirname $(CURRENTDIR))
GO111MODULE=auto

all: clean   build_go 

clean:
	rm go-simple

build_go:
	go build

# Multi-stage docker
# note that in the Dockerfile make all is called !! so it makes in docker
# -build-arg  http_proxy=${HTTP_PROXY} --build-arg https_proxy=${HTTP_PROXY} 
in-docker:
	DOCKER_BUILDKIT=1 docker build -f  Dockerfile --target=testresult --output out  $(CURRENTDIR) 
	DOCKER_BUILDKIT=1 docker build  -t simple-go:1.0 -f  Dockerfile  $(CURRENTDIR) 
	echo " ---- Built Simple Docker Image -- simple-go -----------"

