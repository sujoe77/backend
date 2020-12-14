package com.zs.prime.rest;


import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import akka.actor.typed.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.MediaTypes;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import org.junit.*;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoutesTest extends JUnitRouteTest {

    @ClassRule
    public static TestKitJunitResource testkit = new TestKitJunitResource();

    private static ActorRef<PrimeRegistry.Command> userRegistry;
    private TestRoute appRoute;

    @BeforeClass
    public static void beforeClass() {
        userRegistry = testkit.spawn(PrimeRegistry.create());
    }

    @Before
    public void before() {
        Routes Routes = new Routes(testkit.system(), userRegistry);
        appRoute = testRoute(Routes.routes(testkit.system()));
    }

    @AfterClass
    public static void afterClass() {
        testkit.stop(userRegistry);
    }

    @Test
    public void testGetPrimes() {
        appRoute.run(HttpRequest.GET("/prime/1"))
                .assertStatusCode(StatusCodes.OK)
                .assertMediaType("text/csv")
                .assertEntity("1,\n");
        appRoute.run(HttpRequest.GET("/prime/10"))
                .assertStatusCode(StatusCodes.OK)
                .assertMediaType("text/csv")
                .assertEntity("1,\n2,\n3,\n5,\n7,\n");
    }

    @Test
    public void testGetPrimesFailed() {
        appRoute.run(HttpRequest.GET("/prime/0"))
                .assertStatusCode(StatusCodes.BAD_REQUEST)
                .assertMediaType("text/plain")
                .assertEntity("N should be a positive integer!!");

        appRoute.run(HttpRequest.GET("/prime/-1"))
                .assertStatusCode(StatusCodes.BAD_REQUEST)
                .assertMediaType("text/plain")
                .assertEntity("N should be a positive integer!!");

        appRoute.run(HttpRequest.GET("/prime/abc"))
                .assertStatusCode(StatusCodes.BAD_REQUEST)
                .assertMediaType("text/plain")
                .assertEntity("N should be a positive integer!!");
    }
}
