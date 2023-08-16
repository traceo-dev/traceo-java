package org.traceo.sdk;


import org.traceo.sdk.http.HttpMethod;
import org.traceo.sdk.http.IRequest;


import java.util.HashMap;
import java.util.Map;

public class TestIRequest implements IRequest<TestIRequest.TestModel> {

    public class TestModel {
        TestModel(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        public String name;
        public String surname;
        public int age;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getEndpoint() {
        return "";
    }

    @Override
    public void setEndpoint(String endpoint) {

    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("some-header", "vallllllll");
        headers.put("some-23", "87654");
        headers.put("some-345677", "dfgdfg");
        return headers;
    }

    @Override
    public TestIRequest.TestModel getContent() {
        TestModel model = new TestModel("ala", "kota", 1);
        return model;
    }

    @Override
    public void setContent(TestModel content) {

    }
}
