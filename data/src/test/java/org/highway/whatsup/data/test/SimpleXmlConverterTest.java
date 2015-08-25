package org.highway.whatsup.data.test;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.highway.whatsup.data.entity.koex.KoExCctvEntity;
import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;
import org.simpleframework.xml.stream.Verbosity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;
import retrofit.http.GET;
import rx.Observable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by engeng on 8/24/15.
 */
public class SimpleXmlConverterTest {

    interface Service {
        @GET("/")
        Observable<Response> get();
    }

    public final MockWebServer server = new MockWebServer();

    Service service;

    @Before
    public void setUp() throws Exception {
        server.start();

        Format format = new Format(0, null, new HyphenStyle(), Verbosity.HIGH);
        Persister persister = new Persister(format);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(server.getUrl("/").toString())
                .setConverter(new SimpleXMLConverter(persister))
                .build();
        service = adapter.create(Service.class);
    }

    @Test
    public void testDataConverting() throws ExecutionException, InterruptedException, IOException {
        server.enqueue(new MockResponse().setBody(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<response>\n" +
                        "\t<coordtype>1</coordtype>\n" +
                        "\t<datacount>1461</datacount>\n" +
                        "\t<data>\n" +
                        "\t\t<roadsectionid/>\n" +
                        "\t\t<filecreatetime/>\n" +
                        "\t\t<cctvtype>2</cctvtype>\n" +
                        "\t\t<cctvurl>http://cctvsec.ktict.co.kr/2/j3ANm49h6VXGPDVtwLUdOmGs8G6LCWIqQblyBd8S/Ehiu9z731T6vaYPNABD+eV4</cctvurl>\n" +
                        "\t\t<cctvresolution/>\n" +
                        "\t\t<coordy>37.42889</coordy>\n" +
                        "\t\t<cctvformat>MP4</cctvformat>\n" +
                        "\t\t<cctvname>[서울외곽선] 성남</cctvname>\n" +
                        "\t\t<coordx>127.12361</coordx>\n" +
                        "\t</data>\n" +
                        "\t<data>\n" +
                        "\t\t<roadsectionid/>\n" +
                        "\t\t<filecreatetime/>\n" +
                        "\t\t<cctvtype>1</cctvtype>\n" +
                        "\t\t<cctvurl>cctvurl</cctvurl>\n" +
                        "\t\t<cctvresolution/>\n" +
                        "\t\t<coordy>10</coordy>\n" +
                        "\t\t<cctvformat>MP4</cctvformat>\n" +
                        "\t\t<cctvname>테스트</cctvname>\n" +
                        "\t\t<coordx>20</coordx>\n" +
                        "\t</data>\n" +
                        "</response>"));

        Response r = service.get().toBlocking().toFuture().get();
        assertThat(r.data.size(), is(2));
    }

    @Test
    public void testDataSerialize() throws Exception {
        String XML = "<data>\n" +
                "\t\t<roadsectionid/>\n" +
                "\t\t<filecreatetime/>\n" +
                "\t\t<cctvtype>2</cctvtype>\n" +
                "\t\t<cctvurl>http://cctvsec.ktict.co.kr/2/j3ANm49h6VXGPDVtwLUdOmGs8G6LCWIqQblyBd8S/Ehiu9z731T6vaYPNABD+eV4</cctvurl>\n" +
                "\t\t<cctvresolution/>\n" +
                "\t\t<coordy>37.42889</coordy>\n" +
                "\t\t<cctvformat>MP4</cctvformat>\n" +
                "\t\t<cctvname>[서울외곽선] 성남</cctvname>\n" +
                "\t\t<coordx>127.12361</coordx>\n" +
                "\t</data>";

        Serializer serializer = new Persister();
        KoExCctvEntity cctv = serializer.read(KoExCctvEntity.class, XML);
        assertThat(cctv.getCctvType(), is(2));
        assertThat(cctv.getCoordX(), is(127.12361));
    }

    @Test
    public void testDataSerialize2() throws Exception {
        String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<response>\n" +
                "\t<coordtype>1</coordtype>\n" +
                "\t<datacount>1461</datacount>\n" +
                "\t<data>\n" +
                "\t\t<roadsectionid/>\n" +
                "\t\t<filecreatetime/>\n" +
                "\t\t<cctvtype>1</cctvtype>\n" +
                "\t\t<cctvurl>cctvurl</cctvurl>\n" +
                "\t\t<cctvresolution/>\n" +
                "\t\t<coordy>10</coordy>\n" +
                "\t\t<cctvformat>MP4</cctvformat>\n" +
                "\t\t<cctvname>테스트</cctvname>\n" +
                "\t\t<coordx>20</coordx>\n" +
                "\t</data>\n" +
                "\t<data>\n" +
                "\t\t<roadsectionid/>\n" +
                "\t\t<filecreatetime/>\n" +
                "\t\t<cctvtype>1</cctvtype>\n" +
                "\t\t<cctvurl>cctvurl</cctvurl>\n" +
                "\t\t<cctvresolution/>\n" +
                "\t\t<coordy>10</coordy>\n" +
                "\t\t<cctvformat>MP4</cctvformat>\n" +
                "\t\t<cctvname>테스트</cctvname>\n" +
                "\t\t<coordx>20</coordx>\n" +
                "\t</data>\n" +
                "</response>";

        Serializer serializer = new Persister();
        Response r = serializer.read(Response.class, XML);
        assertThat(r.coordType, is(1));
        assertThat(r.data.size(), is(2));
        assertThat(r.data.get(1).getCctvType(), is(1));
    }

    @Root
    public static class Response {
        @Element(name = "coordtype", required = false)
        int coordType;

        @Element(name = "datacount", required = false)
        int dataCount;

        @ElementList(name = "data", inline = true, required = false)
        List<KoExCctvEntity> data;
    }
}
