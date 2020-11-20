package grpc.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import examples.search.SearchServiceGrpc;
import examples.search.SearchServiceGrpc.SearchServiceBlockingStub;
import examples.search.SearchResponse;
import examples.search.SearchRequest;

/*
Used as a base -
https://github.com/grpc/grpc-java/blob/9e02cf089ee8b3ff635c8531039cfe87da417779/examples/src/
main/java/io/grpc/examples/helloworld/HelloWorldClient.java.
*/

public class GrpcClient {
    private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

    private final ManagedChannel channel;
    private final SearchServiceBlockingStub blockingStub;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS
                // to avoid
                // needing certificates.
                .usePlaintext().build());
    }

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    GrpcClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = SearchServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String searchRequest(String query, String email_id, Integer result_per_page, Integer page_num) {

        logger.info("Sending a search request to server");
        SearchRequest request = SearchRequest.newBuilder().setQuery(query).setEmailId(email_id)
                .setPageNumber(page_num).setResultPerPage(result_per_page).build();

        SearchResponse response;
        try {
            response = blockingStub.search(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            throw e;
        }
        logger.info("Response from Server: " + response.getSearchResponse());
        return response.getSearchResponse();

    }
}
