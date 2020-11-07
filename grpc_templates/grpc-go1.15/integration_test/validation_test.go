package main

import (
	"testing"
	"log"
	"net"
	"time"
	"context"
	"os"
	"google.golang.org/grpc"
	pb "interfaces/grpc-go"
	server "server"
	"google.golang.org/grpc/test/bufconn"
)

const (
	//port = ":50051"
	bufSize = 1024 * 1024
)

var lis *bufconn.Listener

func setup(){
	log.Printf("Go protobuffer test")
		
	/*lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}*/
	// Integration testing GRPC in memory https://stackoverflow.com/a/52080545/429476
	lis = bufconn.Listen(bufSize)
	s := grpc.NewServer()
	pb.RegisterSearchServiceServer(s, &server.Server{})
	go func() {
		if err := s.Serve(lis); err != nil {
			log.Fatalf("failed to serve: %v", err)
		}
	}()
}
func bufDialer(string, time.Duration) (net.Conn, error) {
    return lis.Dial()
}
// this TestMain implements setup and tear down
func TestMain(m *testing.M) {
	setup()
	code := m.Run() 
    os.Exit(code)
}

// Test Positive - proper validation
func TestPositiveValidation(t *testing.T){
	log.Printf("Astrix goes for Peace - Pass good responses")
	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()
	conn, err := grpc.DialContext(ctx,"bufnet", grpc.WithDialer(bufDialer),grpc.WithInsecure())
	if err != nil {
		t.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()
	c := pb.NewSearchServiceClient(conn)
		r, err := c.Search(ctx, &pb.SearchRequest{Query: "Protocol Buffer",EmailId: "alex.test@gmail.com"})
	if err != nil {
		t.Fatalf("could not execute search: %v", err)
	}
	//Lets validate the respose
	log.Printf("Greeting: %s", r.SearchResponse)
}


	
