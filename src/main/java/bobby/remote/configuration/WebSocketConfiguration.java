package bobby.remote.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(10 * 1024 * 1024);
        container.setMaxSessionIdleTimeout(4096L * 4096L);
        container.setAsyncSendTimeout(4096L * 4096L);
        container.setMaxBinaryMessageBufferSize(10 * 1024 * 1024);
        return container;
    }
}
