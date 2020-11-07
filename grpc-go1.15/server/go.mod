module server

go 1.15

require (
	github.com/golang/protobuf v1.4.3 // indirect
	github.com/sirupsen/logrus v1.7.0
	google.golang.org/grpc v1.33.1 // indirect
	google.golang.org/protobuf v1.25.0 // indirect
	interfaces v0.0.0-00010101000000-000000000000
)

replace interfaces => ../generated
