package com.sampleservice.urlfactory;

import java.net.URI;
import java.net.URISyntaxException;

public interface ApiUrlFactory {

	public URI createUserUrl() throws URISyntaxException;
}
