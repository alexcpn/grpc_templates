
package grpc.example.server;

// the below are coming from the generated proto files app/build/generated and given
// as a source in 'sourceSets' in build.gradle

import examples.search.SearchResponse;
import examples.search.SearchRequest;
import examples.search.SearchServiceGrpc.SearchServiceImplBase;

public class GrpcServer extends SearchServiceImplBase {

    @Override
    public void search(SearchRequest request,
            io.grpc.stub.StreamObserver<SearchResponse> responseObserver) {
        System.out.println("Got a request " + request.getQuery() + " email-id " + request.getEmailId());
        SearchResponse response = SearchResponse.newBuilder().setPageNumber(2).
        setSearchResponse("SearchResponse" +request.getEmailId()).setTotalPages(1)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
