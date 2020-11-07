package server

import (
	"context"
	pb "interfaces/grpc-go"

	log "github.com/sirupsen/logrus"
)

// server is used to implement the server
type Server struct{}

func (s *Server) Search(ctx context.Context, in *pb.SearchRequest) (*pb.SearchResponse, error) {
	log.Printf("Received Emailid: %v", in.EmailId)
	log.Printf("Received Query: %v", in.Query)

	// let us see if shadow variables are caught by linter
	n := 0
	if true {
		n := 1
		n++
	}
	print(n)
	
	response := pb.SearchResponse{SearchResponse: "Some Valid response from server "}
	return &response, nil
	
}
