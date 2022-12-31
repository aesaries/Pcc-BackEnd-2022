package config;

import javax.servlet.ServletContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ConfigThymeleaf {

    private TemplateEngine templateEngine;

    public ConfigThymeleaf(final ServletContext ctx) {

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(ctx);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");

        // tiempo en cache
        templateResolver.setCacheTTLMs(3600000L);

        // desactivar la cache, true para activarla
        templateResolver.setCacheable(false);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public ConfigThymeleaf(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }
}