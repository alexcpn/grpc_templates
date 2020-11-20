package grpc.example.server;



import grpc.example.server.GrpcServer;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;

import examples.search.SearchServiceGrpc.SearchServiceBlockingStub;
import examples.search.SearchResponse;
import examples.search.SearchRequest;
import examples.search.SearchServiceGrpc;

@RunWith(JUnit4.class)
public class TestGrpcServer {

    /**
     * This rule manages automatic graceful shutdown for the registered servers and
     * channels at the end of test.
     */
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    /**
     * To test the server, make calls with a real stub using the in-process channel,
     * and verify behaviors or state changes from the client side.
     */
    @Test
    public void grpcServer_test() throws Exception {
        // Generate a unique in-process server name.
        String serverName = InProcessServerBuilder.generateName();
        // Create a server, add service, start, and register for automatic graceful
        // shutdown.
        grpcCleanup.register(InProcessServerBuilder.forName(serverName).directExecutor().addService(new GrpcServer())
                .build().start());

        SearchServiceBlockingStub blockingStub = SearchServiceGrpc.newBlockingStub(
                // Create a client channel and register for automatic graceful shutdown.
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        SearchRequest request = SearchRequest.newBuilder().setQuery("test query").setEmailId("alex@alex.com")
                .setPageNumber(1).setResultPerPage(10).build();
        SearchResponse response = blockingStub.search(request);

        assertEquals("SearchResponse" + request.getEmailId(), response.getSearchResponse());

    }

}