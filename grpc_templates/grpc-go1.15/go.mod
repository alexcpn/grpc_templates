module main

go 1.15

require (
	github.com/golangci/golangci-lint v1.32.2 // indirect
	github.com/jstemmer/go-junit-report v0.9.1 // indirect
	google.golang.org/grpc v1.33.1
	interfaces v0.0.0
	server v0.0.0

)

replace interfaces => ./generated

replace server => ./server
