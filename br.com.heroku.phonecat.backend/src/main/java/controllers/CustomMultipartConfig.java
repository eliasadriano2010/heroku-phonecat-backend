package controllers;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

    // alteramos o tamanho total do upload para 50MB
    public long getSizeLimit() {
        return 50 * 1024 * 1024;
    }

    // alteramos o tamanho do upload de cada arquivo para 20MB
    public long getFileSizeLimit() {
        return 20 * 1024 * 1024;
    }
}