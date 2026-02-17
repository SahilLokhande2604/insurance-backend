//package com.customersupport.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.BufferingClientHttpRequestFactory;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class RestTemplateConfig {
//    
//    @Bean
//    public RestTemplate restTemplate(AppProperties props) {
//        int ct = props.getPolicy().getConnectTimeoutMs();
//        int rt = props.getPolicy().getReadTimeoutMs();
//        
//        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(
//            new SimpleClientHttpRequestFactory() {{
//                setConnectTimeout(ct);
//                setReadTimeout(rt);
//            }}
//        );
//        
//        return new RestTemplate(factory);
//    }
//}

package com.customersupport.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final AppProperties props;
    
    

    public RestTemplateConfig(AppProperties props) {
		super();
		this.props = props;
	}



	@Bean
    public RestTemplate restTemplate() {

        int ct = props.getPolicy().getConnectTimeoutMs();
        int rt = props.getPolicy().getReadTimeoutMs();

        SimpleClientHttpRequestFactory simpleFactory =
                new SimpleClientHttpRequestFactory();

        simpleFactory.setConnectTimeout(ct);
        simpleFactory.setReadTimeout(rt);

        ClientHttpRequestFactory factory =
                new BufferingClientHttpRequestFactory(simpleFactory);

        return new RestTemplate(factory);
    }
}
